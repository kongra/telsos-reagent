module.exports = {
  content: ["./src/**/*.cljs"],
  theme: {
    extend: {},
  },
  corePlugins: {
    preflight: true
  },
  plugins: [
    require('tailwindcss'),
    require('autoprefixer')
  ]
}
