(ns tui.formatting)

(defn padding-str
  "Generate a padding string of specific size. Pad with spaces
by default. Optionally specify padding character"
  ([size]
   (padding-str size \space))
  ([size pad-char]
   (reduce str "" (repeat size pad-char))))

(defn centre-text
  "Generate a string of specific size with text centred. "
  ([txt size]
   (centre-text txt size \space))
  ([txt size pad-char]
   (if (<= size (count txt))
     txt
     (let [pad-size (quot (- size (count txt)) 2)
           prefix-pad (padding-str pad-size pad-char)
           suffix-pad (padding-str (- size (+ (count txt)
                                              (count prefix-pad)))
                                   pad-char)]
       (str prefix-pad txt suffix-pad)))))

(defn left-text
  "generate a string of specific size with text left justified"
  ([txt size]
   (left-text txt size \space))
  ([txt size pad-char]
   (if (<= size (count txt))
     txt
     (let [pad-size (- size (count txt))
           padding  (padding-str pad-size pad-char)]
       (str txt padding)))))

(defn right-text
  ([txt size]
   (right-text txt size \space))
  ([txt size pad-char]
   (if (<= size (count txt))
     txt
     (let [pad-size (- size (count txt))
           padding  (padding-str pad-size pad-char)]
       (str padding txt)))))

(defn find-break-point [txt width]
  (cond
    (< width 1) -1
    (<= (count txt) width) (dec (cound txt))
    :else (loop [pos (dec (min (count txt) width))]
            (cond
              (= pos 0) width
              (= (nth txt pos) \space) pos
              :else (recur (dec pos))))))

(defn wrap-text
  "Wrap text into lines with maximum width. Return vector of strings."
  ([txt width]
   (if (or (< width 1)
           (= 0 (count txt)))
     []
     (wrap-text txt width [])))
  ([txt width block]
   (cond
     (= 0 (count txt)) block
     (<= (count txt) width) (conj block txt)
     :else (let [end (find-break-point txt width)]
             (cond
               (= -1 end) (conj block txt)
               (= (nth txt end) \space) (wrap-text (subs txt (inc end)) width
                                                   (conj block (subs txt 0 end)))
               :else (if (= 1 width)
                       (wrap-text (subs txt end) width
                                  (conj block (subs txt 0 end)))
                       (wrap-text (subs txt (dec end)) width
                                  (conj block (str (subs txt 0 (dec end)) "-")))))))))
