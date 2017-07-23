(ns tui.input
  (:require [lanterna.screen :as scr]
            [tui.windows :as window]))

(defn read-input-line
  "Read line of input from window"
  [win]
  (let [w (window/get-window win)
        [col row] (scr/get-cursor w)]
    (loop [c (scr/get-key-blocking w)
           input ""
           x col
           y row]
      (if (= c :enter)
        input
        (cond
          (char? c) (do
                      (scr/put-string w x y (str c))
                      (scr/move-cursor w (inc x) y)
                      (scr/redraw w)
                      (recur (scr/get-key-blocking w) (str input c)
                             (inc x) y))
          (= c :tab) (do
                       (scr/put-string w x y " ")
                       (scr/move-cursor w (inc x) y)
                       (scr/redraw w)
                       (recur (scr/get-key-blocking w) (str input " ")
                              (inc x) y))
          (= c :backspace) (do
                             (scr/move-cursor w (dec x) y)
                             (scr/put-string w (dec x) y " ")
                             (scr/redraw w)
                             (recur (scr/get-key-blocking w)
                                    (subs input 0 (dec (count input)))
                                    (dec x) y))
          :else (recur (scr/get-key-blocking w) input x y))))))
