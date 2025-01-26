(ns telsos.ui.core
  (:require
   ["react" :as React]
   ["react-dom/client" :as react-client]
   [reagent.core :as r]
   [telsos.ui.commit-hash]
   [telsos.ui.main :refer [ui-main]]))

(defn- render-root
  [root]
  (->> [:> React/StrictMode [ui-main]]
       (r/as-element)
       (.render root)))

(defonce root-atom (atom nil))

(defn after-load []
  (render-root @root-atom))

(defn start []
  (->> "core"
       (js/document.getElementById)
       (react-client/createRoot)
       (reset! root-atom)
       (render-root)))
