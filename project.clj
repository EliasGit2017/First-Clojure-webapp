(defproject basic-webapp "0.1.0"
  :description "Basic webapp to try and learn clojure and heroku. Just a simple app"
  :url "https://serene-refuge-26959.herokuapp.com/"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.6.0"]
                 [com.h2database/h2 "1.4.193"]
                 [hickory "0.7.1"]
                 [markdown-clj "1.10.5"]]
  :plugins [[lein-ring "0.12.5"]]
  :main basic-webapp.handler
  :ring {:handler basic-webapp.handler/app}
  :uberjar-name "clojure-basic-webapp-standalone.jar"
  :profiles {:production {:env {:production true}}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.2"]]}
             :uberjar {:aot :all}})
