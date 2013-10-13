(defproject ci-news "0.1.0-SNAPSHOT"
  :description "No breaking news"
  :url "http://github.com/shishkin/ci-news"
  :license {:name "MIT License"
            :url "http://github.com/shishkin/ci-news/raw/master/LICENSE"}
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1934"]
                 [compojure "1.1.5"]
                 [domina "1.0.2"]]
  :plugins [[lein-cljsbuild "0.3.3"]
            [lein-ring "0.8.7"]]
  :ring {:handler ci-news.server/handler}
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.0"]]
                   :clean-targets ["out"
                                   "resources/public/js/app.js"
                                   "repl"]
                   :repl-options {:nrepl-middleware
                                  [cemerick.piggieback/wrap-cljs-repl]}
                   :injections [(require '[cljs.repl.browser :as brepl]
                                         '[cemerick.piggieback :as pb])
                                (defn browser-repl []
                                  (pb/cljs-repl :repl-env (brepl/repl-env :port 9000)))]
                   :cljsbuild {:builds
                               [{:source-paths ["src/cljs"]
                                 :compiler {:output-to "resources/public/js/app.js"
                                            :optimizations :whitespace
                                            :pretty-print true}}]}}
             :prod {:cljsbuild {:builds
                                [{:source-paths ["src/cljs/ci_news"]
                                  :compiler {:output-to "resources/public/js/app.js"
                                             :optimizations :advanced
                                             :pretty-print false}}]}}})
