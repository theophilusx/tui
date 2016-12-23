(ns tui.windows-test
  (:require [tui.windows :as sut]
            [clojure.test :as t]))

(t/deftest window-base-ops
  (let [w-list (sut/window-list)]
    (t/testing "Create a new window"
      (t/is (= true (instance? com.googlecode.lanterna.screen.Screen
                               (sut/make-window :test 40 10)))))
    (t/testing "Test window utility functions"
      (t/is (= 40 (sut/get-columns :test)))
      (t/is (= 10 (sut/get-rows :test)))
      (t/is (= true (instance? com.googlecode.lanterna.screen.Screen
                               (sut/get-window :test)))))
    (t/testing "Destroy existing window"
      (sut/destroy-window :test)
      (t/is (= nil (sut/get-window :test))))
    (t/testing "Window list back to start value"
      (t/is (= w-list (sut/window-list))))))
