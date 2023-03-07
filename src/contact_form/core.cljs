(ns contact-form.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; Views

(def contact-submitted (r/atom false))

(defn prompt-message
  "A prompt that will animate to help the user with a given input"
  [message]
  [:div {:class "bg-teal-100 border-t-4 border-teal-500 rounded-b text-teal-900 px-4 py-3 shadow-md"}
    [:div {:class "flex"} 
      [:div>p message]
    ]
  ]
)

(defn input-element
  "An input element which updates its value on change"
  [id name type value]
  [:input {:id id
           :name name
           :class "form-control shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
           :type type
           :required true
           :value @value}])

(defn home-page []
  (let [email-address (atom nil) phone-address (atom nil)]
    (fn []
      [:div {:class "h-screen	rounded-t-lg overflow-hidden border-t border-l border-r border-gray-400 px-3 py-10 bg-gray-200 flex justify-center"}

        [:div { :class "w-full max-w-xs"} 
          (if (true? @contact-submitted) 
            [prompt-message "Contact Submitted Successfully!"]
            [:div
              [:form {:class "bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" }
                [:div.mb-4 
                  [:h2 "Contact Us"]
                ]

                [:div.mb-4 
                  [:label {:class="block text-gray-700 text-sm font-bold mb-2"} "Email"]
                  [input-element "email" "email" "email" email-address]
                ]

                [:div.mb-4 
                  [:label {:class="block text-gray-700 text-sm font-bold mb-2"} "Phone Number"]
                  [input-element "phone" "phone" "phone" phone-address]
                ]

                [:button  {:on-click #(reset! contact-submitted true) :class "bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" } "Submit"]
              ]
              [:p {:class "text-center text-gray-500 text-xs" } "©2023 Abbeal Corp. All rights reserved." ]
            ]
          )
        ]
      ]
    )
  )
)


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
