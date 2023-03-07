(ns contact-form.components.input-element)

(defn input-element
  [id name type value]
  [:input {:id id
           :name name
           :class "form-control shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
           :type type
           :required true
           :value @value}])

