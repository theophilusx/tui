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
     (alter active-windows assoc-in [win :window-columns] cols)
     (alter active-windows assoc-in [win :window-rows] rows))))


(defn make-window
  "Create a new window"
  [win-name cols rows]
  (let [w (scr/get-screen :swing
                          {:cols cols
                           :rows rows
                           :resize-listener (make-resize-handler win-name)})
        info {:window-columns cols
              :window-rows rows
              :cursor-column 0
              :cursor-row 0
              :window w}]
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

(defn get-window-columns
  "Return current columns for window"
  [win]
  (get-in @active-windows [win :window-columns]))

(defn set-cursor-column
  "Set the cursor column position"
  [win col]
  (dosync
   (alter active-windows assoc-in [win :cursor-column] col)))

(defn get-cursor-column
  "Get the current cursor column"
  [win]
  (get-in @active-windows [win :cursor-column]))

(defn get-window-rows
  "Return current rows for window"
  [win]
  (get-in @active-windows [win :window-rows]))

(defn set-cursor-row
  "Set the current cursor row"
  [win row]
  (dosync
   (alter active-windows assoc-in [win :cursor-row] row)))

(defn get-cursor-row
  "Get the cursor row"
  [win]
  (get-in @active-windows [win :cursor-row]))

(defn set-cursor
  "Set the cursor"
  [win col row]
  (dosync
   (alter active-windows assoc-in [win :cursor-column] col)
   (alter active-windows assoc-in (win :cursor-row) row)))

(defn get-cursor
  "Get the current window cursor"
  [win]
  [(get-cursor-column win) (get-cursor-row win)])

(defn destroy-window
  "Destroy a window"
  [win]
  (let [w (get-window win)]
    (when w
      (scr/stop w)
      (dosync
       (alter active-windows dissoc win)))))
