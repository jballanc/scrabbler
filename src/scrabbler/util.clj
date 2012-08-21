(ns scrabbler.util
  (:use [clojure.string :only [split-lines upper-case]]))

(def points-per-letter
  {\A 1 \B 3 \C 3 \D 2 \E 1 \F 4 \G 2 \H 4 \I 1 \J 8
   \K 5 \L 1 \M 3 \N 1 \O 1 \P 3 \Q 10 \R 1 \S 1 \T 1
   \U 1 \V 4 \W 4 \X 8 \Y 4 \Z 10})

(def full-tile-set
  (str "EEEEEEEEEEEEAAAAAAAAAIIIIIIIIIOOOOOOOONNNNNNRRRRRRTTTTTTLLLLSSSSUUUUDDD"
       "DGGGBBCCMMPPFFHHVVWWYYKJXQZ"))

(def dictionary (memoize (fn
  ([] (dictionary "/usr/share/dict/words"))
  ([file] (split-lines (upper-case (slurp file)))))))

(defn score [word]
  (let [word (seq (upper-case word))
        tally (fn [total letter]
               (+ total (get points-per-letter letter 0)))]
    (reduce tally 0 word)))
