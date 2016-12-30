(ns tui.utils
  (:require [lanterna.screen :as scr]
            [tui.windows :as w]
            [tui.formatting :as f]))

(defn write-block [screen block col row]
  (loop [b block r row]
    (when (seq b)
      (scr/put-string screen col r (first b))
      (recur (rest b) (inc r)))))

(defn clear-screen
  ([screen]
   (clear-screen screen \space 0 (w/get-rows screen)))
  ([screen pad-char]
   (clear-screen screen pad-char 0 (w/get-rows screen)))
  ([screen pad-char start-row]
   (clear-screen screen pad-char start-row (w/get-rows screen)))
  ([screen pad-char start-row end-row]
   (let [cols    (inc (w/get-columns screen))
         padding (reduce str "" (repeat cols pad-char))]
     (loop [row start-row]
       (when (< row end-row)
         (scr/put-string screen 0 row padding)
         (recur (inc row)))))))

(defn clear-line
  "Clear the line from col to end"
  [win col row]
  (let [width (- (w/get-columns win) col)
        pad-str (f/padding-str width)]
    (scr/put-string (w/get-window win) col row pad-str)))

(defn command-prompt
  "Simple Command Prompt. Returns entered line"
  [win prompt row]
  (let [col (+ 2 (count prompt))]
    (scr/put-string (w/get-window win) 0 row (str prompt ": "))
    (clear-line win col row)
    (scr/move-cursor (w/get-window win) col row)))
