(ns autheos-challenge.core
  (:use clojure.test))

(defn number->english [n]
  (let [english (clojure.pprint/cl-format nil "~R" n)
        words (clojure.string/split english #"\s")]
    (if (and (not= (rem n 100) 0)
             (< 2 (count words)))
      (clojure.string/join " " (concat (butlast words) ["and" (last words)]))
      english)))

(deftest test-number-to-english
  (is (= (number->english 123) "one hundred and twenty-three"))
  (is (= (number->english 342) "three hundred and forty-two"))
  (is (= (number->english 115) "one hundred and fifteen"))
  (is (= (number->english 200) "two hundred"))
  (is (= (number->english 220) "two hundred and twenty"))
  (is (= (number->english 100000) "one hundred thousand"))
  (is (= (number->english 999999) "nine hundred ninety-nine thousand, nine hundred and ninety-nine"))
  (is (= (number->english 1000000) "one million")))