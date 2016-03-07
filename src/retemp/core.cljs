;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-12

(ns ^:figwheel-always retemp.core
    (:require [reagent.core :as r]))

(enable-console-print!)

;; VIEW

(defn main-view
  []
  [:h2.sample "Testowy napis"])


;; INSTRUMENTATION

(defn on-js-reload []
  (println "Reloaded...")
  (r/render-component [main-view] (. js/document (getElementById "app"))))


(defn init []
  (on-js-reload))


(defonce start
  (init))
