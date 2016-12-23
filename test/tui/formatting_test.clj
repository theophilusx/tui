(ns tui.formatting-test
  (:require [tui.formatting :as sut]
            [clojure.test :as t]))

(t/deftest padding-str
  (t/testing "Generate string of spaces"
    (t/is (= "" (sut/padding-str 0)))
    (t/is (= " " (sut/padding-str 1)))
    (t/is (= "  " (sut/padding-str 2)))
    (t/is (= "   " (sut/padding-str 3)))
    (t/is (= "" (sut/padding-str -1))))
  (t/testing "Generate string of = characters"
    (t/is (= "" (sut/padding-str 0 \=)))
    (t/is (= "=" (sut/padding-str 1 \=)))
    (t/is (= "==" (sut/padding-str 2 \=)))
    (t/is (= "===" (sut/padding-str 3 \=)))
    (t/is (= "" (sut/padding-str -1 \=)))))

(t/deftest center-text
  (t/testing "Generate string with centred text"
    (t/is (= "a" (sut/centre-text "a" 0)))
    (t/is (= "a" (sut/centre-text "a" 1)))
    (t/is (= "a " (sut/centre-text "a" 2)))
    (t/is (= " a " (sut/centre-text "a" 3)))
    (t/is (= " a  " (sut/centre-text "a" 4)))
    (t/is (= "  a  " (sut/centre-text "a" 5)))
    (t/is (= "  a   " (sut/centre-text "a" 6)))
    (t/is (= "a" (sut/centre-text "a" -1))))
  (t/testing "Generate string with centred text and specified pad char"
    (t/is (= "a" (sut/centre-text "a" 0 \-)))
    (t/is (= "a" (sut/centre-text "a" 1 \-)))
    (t/is (= "a-" (sut/centre-text "a" 2 \-)))
    (t/is (= "-a-" (sut/centre-text "a" 3 \-)))
    (t/is (= "-a--" (sut/centre-text "a" 4 \-)))
    (t/is (= "--a--" (sut/centre-text "a" 5 \-)))
    (t/is (= "--a---" (sut/centre-text "a" 6 \-)))
    (t/is (= "a" (sut/centre-text "a" -1 \-))))
  (t/testing "Generate string with centred text and specified pad char"
    (t/is (= "ab" (sut/centre-text "ab" 0 \-)))
    (t/is (= "ab" (sut/centre-text "ab" 1 \-)))
    (t/is (= "ab" (sut/centre-text "ab" 2 \-)))
    (t/is (= "ab-" (sut/centre-text "ab" 3 \-)))
    (t/is (= "-ab-" (sut/centre-text "ab" 4 \-)))
    (t/is (= "-ab--" (sut/centre-text "ab" 5 \-)))
    (t/is (= "--ab--" (sut/centre-text "ab" 6 \-)))
    (t/is (= "ab" (sut/centre-text "ab" -1 \-)))))

(t/deftest left-text
  (t/testing "Generate string with left justified text"
    (t/testing "Generate single char left justified text"
      (t/is (= "a" (sut/left-text "a" -1)))
      (t/is (= "a" (sut/left-text "a" 0)))
      (t/is (= "a" (sut/left-text "a" 1)))
      (t/is (= "a " (sut/left-text "a" 2)))
      (t/is (= "a  " (sut/left-text "a" 3)))
      (t/is (= "a   " (sut/left-text "a" 4)))
      (t/is (= "a    " (sut/left-text "a" 5)))
      (t/is (= "a     " (sut/left-text "a" 6))))
    (t/testing "Generate single char left justified with specified pad"
      (t/is (= "a" (sut/left-text "a" -1 \=)))
      (t/is (= "a" (sut/left-text "a" 0 \=)))
      (t/is (= "a" (sut/left-text "a" 1 \=)))
      (t/is (= "a=" (sut/left-text "a" 2 \=)))
      (t/is (= "a==" (sut/left-text "a" 3 \=)))
      (t/is (= "a===" (sut/left-text "a" 4 \=)))
      (t/is (= "a====" (sut/left-text "a" 5 \=)))
      (t/is (= "a=====" (sut/left-text "a" 6 \=))))
    (t/testing "Generate left justified text"
      (t/is (= "ab" (sut/left-text "ab" -1 \=)))
      (t/is (= "ab" (sut/left-text "ab" 0 \=)))
      (t/is (= "ab" (sut/left-text "ab" 1 \=)))
      (t/is (= "ab" (sut/left-text "ab" 2 \=)))
      (t/is (= "ab=" (sut/left-text "ab" 3 \=)))
      (t/is (= "ab==" (sut/left-text "ab" 4 \=)))
      (t/is (= "ab===" (sut/left-text "ab" 5 \=)))
      (t/is (= "ab====" (sut/left-text "ab" 6 \=))))))

(t/deftest right-text
  (t/testing "Generate string with right justified text"
    (t/testing "Generate single char right justified text"
      (t/is (= "a" (sut/right-text "a" -1)))
      (t/is (= "a" (sut/right-text "a" 0)))
      (t/is (= "a" (sut/right-text "a" 1)))
      (t/is (= " a" (sut/right-text "a" 2)))
      (t/is (= "  a" (sut/right-text "a" 3)))
      (t/is (= "   a" (sut/right-text "a" 4)))
      (t/is (= "    a" (sut/right-text "a" 5)))
      (t/is (= "     a" (sut/right-text "a" 6))))
    (t/testing "Generate single char right justified with specified pad"
      (t/is (= "a" (sut/right-text "a" -1 \=)))
      (t/is (= "a" (sut/right-text "a" 0 \=)))
      (t/is (= "a" (sut/right-text "a" 1 \=)))
      (t/is (= "=a" (sut/right-text "a" 2 \=)))
      (t/is (= "==a" (sut/right-text "a" 3 \=)))
      (t/is (= "===a" (sut/right-text "a" 4 \=)))
      (t/is (= "====a" (sut/right-text "a" 5 \=)))
      (t/is (= "=====a" (sut/right-text "a" 6 \=))))
    (t/testing "Generate right justified text"
      (t/is (= "ab" (sut/right-text "ab" -1 \=)))
      (t/is (= "ab" (sut/right-text "ab" 0 \=)))
      (t/is (= "ab" (sut/right-text "ab" 1 \=)))
      (t/is (= "ab" (sut/right-text "ab" 2 \=)))
      (t/is (= "=ab" (sut/right-text "ab" 3 \=)))
      (t/is (= "==ab" (sut/right-text "ab" 4 \=)))
      (t/is (= "===ab" (sut/right-text "ab" 5 \=)))
      (t/is (= "====ab" (sut/right-text "ab" 6 \=))))))

(t/deftest find-break-point
  (t/testing "Find next break point going backwards from specified pos"
    (t/is (= -1 (sut/find-break-point "0123456789" 11)))
    (t/is (= 11 (sut/find-break-point "0123456789AB" 11)))
    (t/is (= 9 (sut/find-break-point "012345678 ABC" 11)))
    (t/is (= 8 (sut/find-break-point "01234567 9ABC" 11)))
    (t/is (= 7 (sut/find-break-point "0123456 89ABC" 11)))
    (t/is (= 1 (sut/find-break-point "0 23456789ABC" 11)))
    (t/is (= 11 (sut/find-break-point " 123456789ABC" 11)))
    (t/is (= 11 (sut/find-break-point "01234567890A" 11)))))

(defn check-block [block size]
  (and (not (empty? block))
       (every? (fn [x]
                 (let [c (count x)]
                   (and (< 0 c) (<= c size)))) block)))

(t/deftest wrap-text
  (t/testing "Test generation of wrap text block"
    (let [txt "The quick brown fox jumped over the lazy dogs 11 times"]
      (t/is (= false (check-block (sut/wrap-text txt 0) 0)))
      (t/is (= true (check-block (sut/wrap-text txt 1) 1)))
      (t/is (= true (check-block (sut/wrap-text txt 2) 2)))
      (t/is (= true (check-block (sut/wrap-text txt 3) 3)))
      (t/is (= true (check-block (sut/wrap-text txt 4) 4)))
      (t/is (= true (check-block (sut/wrap-text txt 8) 8)))
      (t/is (= true (check-block (sut/wrap-text txt 9) 9)))
      (t/is (= true (check-block (sut/wrap-text txt 10) 10)))
      (t/is (= true (check-block (sut/wrap-text txt 15) 15)))
      (t/is (= true (check-block (sut/wrap-text txt 60) 60)))
      (t/is (= false (check-block (sut/wrap-text txt -1) -1)))
      (t/is (= false (check-block (sut/wrap-text "" 10) 10))))))
