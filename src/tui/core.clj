(ns tui.core
  (:require [lanterna.screen :as scr]
            [tui.windows :as w]
            [tui.formatting :as f]
            [tui.input :as i]
            [tui.utils :as u]))

(defn example-app
  "Simple example of using this library"
  []
  (let [w1 (w/make-window :w1 60 20)
        win-cols (w/get-window-columns :w1)]
    (scr/start w1)
    (u/write-line :w1 (f/centre-text "Title" win-cols))
    (u/write-line :w1 (f/centre-text "-----" win-cols))
    (w/set-cursor :w1 0 3)
    (u/write-line :w1 (f/left-text "Text to the left" win-cols))
    (u/write-line :w1 (f/right-text "Text to the right" win-cols))
    (u/write-text :w1 "Write some text")
    (u/write-text :w1 "Write some more text")
    (u/write-text :w1 "Now the quick brown fox jumped over the lazy dogs")
    (w/set-cursor :w1 0 (+ 2 (w/get-cursor-row :w1)))
    (u/write-line :w1 (f/centre-text "Hit 'q' and 'enter' to quit" win-cols))
    (u/command-prompt :w1 "Command" 8)
    (scr/redraw w1)
    (loop [input (i/read-input-line w1)]
      (when-not (= "q" input)
        (println (str "You entered: " input))
        (u/command-prompt :w1 "Command" 8)
        (scr/redraw w1)
        (recur (i/read-input-line w1))))
    (println "You entered q")))


(defn -main
  "I don't do a whole lot."
  [ & x]
  (println x "Hello, World!"))
