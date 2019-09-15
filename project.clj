(defproject asynctor "0.1.0"
  :description "Minimal core.async inspector library for Clojure(Script)."
  :url "https://github.com/ertugrulcetin/asynctor"
  :license {:name "Apache License" :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.clojure/core.async "0.4.500"]]
  :repl-options {:init-ns asynctor.core})
