watch-app:
	@git rev-parse HEAD > resources/public/.commit_hash
	@npx shadow-cljs watch app

release:
	@git rev-parse HEAD > resources/public/.commit_hash
	@npx shadow-cljs release prod
	@npx @tailwindcss/cli -i ./src/css/main.css -o resources/public/assets/css/main.css --minify

watch-css:
	@npx @tailwindcss/cli -i ./src/css/main.css -o resources/public/assets/css/main.css --watch

clean:
	@rm -rf resources/public/assets/

clean-all:
	@rm -rf resources/public/assets/
	@rm -rf node_modules/
	@rm -rf .shadow-cljs/
	@rm -f package-lock.json

clj-kondo:
	@clj-kondo --config .clj-kondo/config.edn --lint  src/
	@clj-kondo --config .clj-kondo/config.edn --lint test/

cloc:
	@cloc . --exclude-list-file=cloc.excluded
