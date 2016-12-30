(ns tui.input
  (:require [lanterna.screen :as scr]))

(defn read-input-line
  "Read line of input from window"
  [win]
  (let [[col row] (scr/get-cursor win)]
    (loop [c     (scr/get-key-blocking win)
           input ""
           x col
           y row]
      (if (= c :enter)
        input
        (cond
          (char? c)        (do
                             (scr/put-string win x y (str c))
                             (scr/move-cursor win (inc x) y)
                             (scr/redraw win)
                             (recur (scr/get-key-blocking win) (str input c)
                                    (inc x) y))
          (= c :tab)       (do
                             (scr/put-string win x y " ")
                             (scr/move-cursor win (inc x) y)
                             (scr/redraw win)
                             (recur (scr/get-key-blocking win) (str input " ")
                                    (inc x) y))
          (= c :backspace) (do
                             (scr/move-cursor win (dec x) y)
                             (scr/put-string win (dec x) y " ")
                             (scr/redraw win)
                             (recur (scr/get-key-blocking win)
                                    (subs input 0 (dec (count input)))
                                    (dec x) y))
          :else            (recur (scr/get-key-blocking win) input x y))))))
