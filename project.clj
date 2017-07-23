(defproject tui "0.1.0-SNAPSHOT"
  :description "Example library using Clojure Lanterna"
  :url "http://github.com/theophilusx/tui.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clojure-lanterna "0.9.7"]]
  :main ^:skip-aot tui.core
  :target-path "target/%s")
