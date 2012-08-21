(ns scrabbler.bruteforce
  (:use [clojure.string :only [upper-case]]
        [scrabbler.util :only [points-per-letter dictionary score]]))

(try
  (require '[clojure.core.reducers :as r])
  (use '[clojure.core.reducers :only [fold]])
  (println "Go Reducers!")
  (catch Throwable _
    (require '[clojure.core :as r])
    (def fold reduce)))

(defn possible-with? [letters word]
  (let [letter-freqs (frequencies (upper-case letters))
        word-lett-freqs (frequencies (seq (upper-case word)))]
    (letfn [(take-letters [[lett freq]]
              (- freq (get word-lett-freqs lett 0)))
            (remaining?
              ([]
               true)
              ([r c]
               (and r (not (neg? c)))))]
      (r/reduce remaining? (r/map take-letters letter-freqs)))))

(defn best-match [letters]
  (letfn [(best
            ([]
             "")
            ([best next]
            (if (> (score best) (score next))
              best
              next)))
          (possible? [word]
            (possible-with? letters word))]
  (fold best (r/filter possible? (dictionary)))))
