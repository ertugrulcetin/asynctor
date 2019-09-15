(ns asynctor.core
  #?(:clj (:require [clojure.core.async :as async]))
  #?(:cljs (:require [cljs.core.async :as async])))


(defn inspect
  [ch]
  #?(:clj  {:buffer (or (some-> ch .buf .buf seq reverse vec) [])
            :puts (mapv second (.puts ch))
            :takes (vec (.takes ch))}
     :cljs {:buffer (or (some-> ch .-buf .-buf .-arr (js->clj :keywordize-keys true)) [])
            :puts   (reduce (fn [acc p]
                              (if p
                                (conj acc (.-val p))
                                acc)) [] (js->clj (.-arr (.-puts ch)) :keywordize-keys true))
            :takes  (reduce (fn [acc t]
                              (if t
                                (conj acc t)
                                acc)) [] (js->clj (.-arr (.-takes ch)) :keywordize-keys true))}))


(defn buffer
  [ch]
  (:buffer (inspect ch)))


(defn puts
  [ch]
  (:puts (inspect ch)))


(defn takes
  [ch]
  (:takes (inspect ch)))


(comment
  ;;=> {:buffer [0 1 2 3 4 5 6 7 8 9], :puts [], :takes []}
  (let [c (async/chan 10)]
    (dotimes [x 10]
      (async/>!! c x))
    (inspect c))

  (let [c (async/chan)]
    (dotimes [x 10]
      (async/put! c x))
    (puts c))

  (let [c (async/chan)]
    (dotimes [x 10]
      (async/take! c (fn [] )))
    (takes c)))