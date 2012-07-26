(ns scrabbler.bruteforce
  (:use [clojure.string :only [upper-case]]
        [scrabbler.util :only [points-per-letter dictionary score]]))

(defn possible-with? [letters word]
  (loop [lett-freq (frequencies (upper-case letters))
         word      (seq (upper-case word))]
    (if (empty? word)
      true
      (let [next-lett (first word)
            new-freq (dec (get lett-freq next-lett 0))]
        (if (< new-freq 0)
          false
          (recur (assoc lett-freq next-lett new-freq)
                 (rest word)))))))

(defn best-match [letters]
  (reduce (fn [best next]
            (if (> (score best) (score next))
              best
              next))
          (filter (fn [word]
                    (possible-with? letters word))
                  dictionary)))
