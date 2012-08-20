(ns scrabbler.core
  (:gen-class)
  (:use [scrabbler.util :only [score]])
  (:require [scrabbler.bruteforce :as bf]))

(defn main [letters matcher]
  (let [letters (seq letters)
        best-match (matcher letters)]
    (println "Looking for the highest scoring word using: " (apply str letters))
    (println "Highest scoring match: " best-match)
    (println "Score: " (score best-match))))

(defn -main [& args]
  (if (seq? args)
    (time (main args bf/best-match))
    (println "Usage: java -jar scrabbler.jar LETTERS")))

