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

(defn possible-with? [tiles word]
  (let [tile-freqs (frequencies (upper-case tiles))
        word-lett-freqs (frequencies (upper-case word))]
    (letfn [(take-tiles [[lett freq]]
              (- (get tile-freqs lett 0) freq))
            (remaining?
              ([]
               true)
              ([r n]
               (and r n)))]
      (r/reduce remaining? (r/map (comp not neg?) (r/map take-tiles word-lett-freqs))))))

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
