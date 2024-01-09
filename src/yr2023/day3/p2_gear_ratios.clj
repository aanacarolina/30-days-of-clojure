(ns yr2023.day3.p2-gear-ratios
  (:require [clojure.string :as str]))

;parse 
;split by digits juntos
;ponto
;anything que nao seja ponto e digit

;criar matriz/map
(def data-list '({:x 0, :y 0, :value "467", :type :digit}
                {:x 5, :y 0, :value "114", :type :digit}
                {:x 3, :y 1, :value "*", :type :symbol}
                {:x 2, :y 2, :value "35", :type :digit}
                {:x 6, :y 2, :value "633", :type :digit}
                {:x 6, :y 3, :value "#", :type :symbol}
                {:x 0, :y 4, :value "617", :type :digit}
                {:x 3, :y 4, :value "*", :type :symbol}
                {:x 5, :y 5, :value "+", :type :symbol}
                {:x 7, :y 5, :value "58", :type :digit}
                {:x 2, :y 6, :value "592", :type :digit}
                {:x 6, :y 7, :value "755", :type :digit}
                {:x 3, :y 8, :value "$", :type :symbol}
                {:x 5, :y 8, :value "*", :type :symbol}
                {:x 1, :y 9, :value "664", :type :digit}
                {:x 5, :y 9, :value "598", :type :digit}))



;story 
;x = line 
;y = column

;is-adjacent - 1st narrow down by line of symbol is +1/-1 in relation to the number

;filter by is-adjacent?

;sum total


(defn input->data [file]
  (str/split-lines (slurp file)))

;(+ x (count (re-find #"\d+" s)))
;(parse-long (str s (take-while #(Character/isDigit %) s))) 


(defn parse-data [y s]
  (loop [result []
         x 0]
    (cond
      ;checks if eol
      (>= x (count s))
      result
      (= (subs s x (inc x)) ".")
      (recur
       result
       (inc x))
      (-> s
          (.charAt x) 
          Character/isDigit)
      (let [v (apply str (take-while #(Character/isDigit %) (subs s x)))]
        (recur
         (conj result
               {:x x
                :y y
                :value (parse-long v)
                :width (count v)
                :type :part})
         (+ x (count v))))
      :else
      (recur
       (conj result {:x x :y y :value (subs s x (inc x)) :type :symbol})
       (inc x)))))

(defn parts-list [data-list]
  (filter #(= (:type %) :part) data-list))

(defn symbols-list [data-list]
  (filter #(= (:type %) :symbol) data-list))

(defn adjacent-locations [x y width]
  (for [ adj-x (range (dec x) (+ x width 1))  
        adj-y (range (dec y) (+ y 2))]
    [adj-x adj-y])) 



(defn is-valid-part? [symbols-locations part]
  (let [{:keys [x y width]} part
        part-adj-locations (adjacent-locations x y width)]
    (some symbols-locations part-adj-locations)
    ))
#_(cada part tem todas as relacoes de adjacencias calculadas;
se na sua relacao de adjacencia, tiver uma outra part
verificarmos se entre as suas relacoes de adjacencia, tem algum simbolo
se tiver, ele entra na conta
se não, vai pra proxima part do mapa)

(defn symbol-location [symbol]
  [(:x symbol) (:y symbol)])

(defn process-gears [input]
  (let [nodes (->> (input->data input)
                   (map-indexed parse-data)
                   (reduce concat))
        parts (parts-list nodes)
        symbols (set (map symbol-location (symbols-list nodes)))]
    (->> parts
         (filter #(is-valid-part? symbols %))
         (map :value)
         (reduce + ))
         ))

(comment

  (process-gears "inputs/day3_exinput.txt")
  (process-gears "inputs/day3_input.txt")
)

