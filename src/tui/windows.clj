(ns tui.windows
  (:require [lanterna.screen :as scr]))

(def active-windows
  "List of active windows"
  (ref {}))

(defn make-resize-handler
  "Return a resize handler for the specified window"
  [win]
  (fn [cols rows]
    (dosync
     (alter active-windows assoc-in [win :cols] cols)
     (alter active-windows assoc-in [win :rows] rows))))


(defn make-window
  "Create a new window"
  [win-name cols rows]
  (let [w (scr/get-screen :swing
                          {:cols cols
                           :rows rows
                           :resize-listener (make-resize-handler win-name)})
        info {:cols cols :rows rows :window w}]
    (dosync
     (alter active-windows assoc win-name info))
    w))

(defn window-list
  "List active windows"
  []
  (keys @active-windows))

(defn get-window
  "Return window handler"
  [win]
  (get-in @active-windows [win :window]))

(defn get-columns
  "Return current columns for window"
  [win]
  (get-in @active-windows [win :cols]))

(defn get-rows
  "Return current rows for window"
  [win]
  (get-in @active-windows [win :rows]))

(defn destroy-window
  "Destroy a window"
  [win]
  (let [w (get-window win)]
    (when w
      (scr/stop w)
      (dosync
       (alter active-windows dissoc win)))))
