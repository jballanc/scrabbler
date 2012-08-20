(defproject scrabbler "0.1.0"
            :description "A simple tool to find the best Scrabble match for a set of letters"
            :url "https://github.com/jballanc/scrabbler"
            :license {:name "BSD 2-Clause License"
                      :url "http://opensource.org/licenses/BSD-2-Clause"}
            :dependencies [[org.clojure/clojure "1.4.0"]]
            :profiles {:1.5 {:dependencies [[org.clojure/clojure "1.5.0-master-SNAPSHOT"]]}}
            :main scrabbler.core)
