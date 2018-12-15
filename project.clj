(defproject retemp "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure          "1.9.0"]
                 [reagent                      "0.8.1"]
                 [org.clojure/clojurescript "1.10.439"]
                 [org.clojure/core.async     "0.4.490"]]

  :plugins      [[lein-cljsbuild            "1.1.7"]
                 [lein-figwheel             "0.5.17"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  ;; Only until javax.xml.bind.DatatypeConverter dep. deprecated in Java 9
  ;; will be removed from figwheel
  ;; :jvm-opts ["--add-modules" "java.xml.bind"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :figwheel {:on-jsload            "retemp.core/on-js-reload"}
              :compiler {:main                 retemp.core
                         :asset-path           "js/compiled/out"
                         :output-to            "resources/public/js/compiled/retemp.js"
                         :output-dir           "resources/public/js/compiled/out"
                         :source-map-timestamp true }}

             {:id "min"
              :source-paths ["src"]
              :compiler {:output-to            "resources/public/js/compiled/retemp.js"
                         :main                  retemp.core
                         :optimizations         :advanced
                         :pretty-print          false }}]}

  :figwheel {:css-dirs ["resources/public/css"]})
