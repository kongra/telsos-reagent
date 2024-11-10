watch:
	@npx shadow-cljs watch dev

release:
	@npx shadow-cljs release prod

clean:
	@rm -rf resources/public/assets/

clj-kondo:
	@clj-kondo --config .clj-kondo/config.edn --lint  src/
	@clj-kondo --config .clj-kondo/config.edn --lint test/

cloc:
	@cloc . --exclude-list-file=cloc.excluded
