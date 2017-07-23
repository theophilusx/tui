(ns tui.core
  (:require [lanterna.screen :as scr]
            [tui.windows :as w]
            [tui.formatting :as f]
            [tui.input :as i]
            [tui.utils :as u]))

(defn get-command 
  "Display a prompt and return value entered by user"
  [window prompt row]
  (u/command-prompt window prompt row)
  (w/redraw-window window)
  (i/read-input-line window))

(defn example-app
  "Simple example of using this library"
  []
  (let [w1 (w/make-window :w1 60 20)
        win-cols (w/get-window-columns :w1)]
    (w/create-window :w1)
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
    (loop [input (get-command :w1 "Command" 8)]
      (when-not (= "q" input)
        (println (str "You entered: " input))
        (u/write-text :w1 (str "You entered: " input) 2 10)
        (recur (get-command :w1 "Command" 8))))
    (println "You entered q")
    (w/destroy-window :w1)))

(defn -main
  "I don't do a whole lot."
  [ & x]
  (example-app))
