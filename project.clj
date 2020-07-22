(defproject basic-webapp "0.1.0"
  :description "Basic webapp to try and learn clojure and heroku. Just a simple app"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.6.0"]
                 [com.h2database/h2 "1.4.193"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler basic-webapp.handler/app}
  :profiles
  :main basic-webapp.handler
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
