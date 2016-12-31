(ns tui.windows-test
  (:require [tui.windows :as sut]
            [clojure.test :as t]))

(t/deftest window-base-ops
  (let [w-list (sut/window-list)]
    (t/testing "Create a new window"
      (t/is (= true (instance? com.googlecode.lanterna.screen.Screen
                               (sut/make-window :test 40 10)))))
    (t/testing "Test window utility functions"
      (t/is (= 40 (sut/get-window-columns :test)))
      (t/is (= 10 (sut/get-window-rows :test)))
      (t/is (= [40 10] (sut/get-window-size :test)))
      (t/is (= true (instance? com.googlecode.lanterna.screen.Screen
                               (sut/get-window :test)))))
    (t/testing "Window cursor operations"
      (t/is (= 0 (sut/get-cursor-column :test)))
      (t/is (= 0 (sut/get-cursor-row :test)))
      (t/is (= 20 (get-in (sut/set-cursor-column :test 20) [:test :cursor-column])))
      (t/is (= 5 (get-in (sut/set-cursor-row :test 5) [:test :cursor-row])))
      (t/is (= 20 (sut/get-cursor-column :test)))
      (t/is (= 5 (sut/get-cursor-row :test)))
      (t/is (= [20 5] (sut/get-cursor :test))))
    (t/testing "Destroy existing window"
      (sut/destroy-window :test)
      (t/is (= nil (sut/get-window :test))))
    (t/testing "Window list back to start value"
      (t/is (= w-list (sut/window-list))))))
