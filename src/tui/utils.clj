(ns tui.utils
  (:require [lanterna.screen :as scr]
            [tui.windows :as w]
            [tui.formatting :as f]))

(defn write-text
  "Write line of text to window starting at cursor position"
  ([win txt]
   (let [[col row] (w/get-cursor win)]
     (write-text win txt col row)))
  ([win txt col]
   (let [row (w/get-cursor-row win)]
     (write-text win txt col row)))
  ([win txt col row]
   (let [[win-cols win-rows] (w/get-window-size win)
         w (w/get-window win)
         txt-size (count txt)]
     (cond
       ;; at last column in row
       (= col (dec win-cols)) (write-text win txt 0 (inc row))
       ;; at last row in window
       (= row (dec win-rows)) (write-text win txt 0 0)
       ;; line too long - break into two
       (< win-cols txt-size) (let [avail-space (- win-cols col)
                                   [c r] (write-text win (subs txt 0 avail-space) col row)]
                               (write-text win (subs txt avail-space) 0 (inc r)))
       ;; just right
       (and (<= txt-size (- win-cols col))
            (< row win-rows)) (do
                                 (scr/put-string w col row txt)
                                 [(+ txt-size col) row])
       :else (do (println "write-text: Something has gone wrong!")
                 [-1 -1])))))

(defn write-line
  "Write a line of text to the window"
  [win txt]
  (let [[col row] (write-text win txt)]
    (w/set-cursor win 0 (inc row))))

(defn write-block [screen block col row]
  (loop [b block r row]
    (when (seq b)
      (scr/put-string screen col r (first b))
      (recur (rest b) (inc r)))))

(defn clear-screen
  ([screen]
   (clear-screen screen \space 0 (w/get-window-rows screen)))
  ([screen pad-char]
   (clear-screen screen pad-char 0 (w/get-window-rows screen)))
  ([screen pad-char start-row]
   (clear-screen screen pad-char start-row (w/get-window-rows screen)))
  ([screen pad-char start-row end-row]
   (let [cols    (inc (w/get-window-columns screen))
         padding (reduce str "" (repeat cols pad-char))]
     (loop [row start-row]
       (when (< row end-row)
         (scr/put-string screen 0 row padding)
         (recur (inc row)))))))

(defn clear-line
  "Clear the line from col to end"
  [win col row]
  (let [width (- (w/get-window-columns win) col)
        pad-str (f/padding-str width)]
    (scr/put-string (w/get-window win) col row pad-str)))

(defn command-prompt
  "Simple Command Prompt. Returns entered line"
  [win prompt row]
  (let [col (+ 2 (count prompt))]
    (scr/put-string (w/get-window win) 0 row (str prompt ": "))
    (clear-line win col row)
    (scr/move-cursor (w/get-window win) col row)))
