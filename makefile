dev:
	@git rev-parse HEAD > resources/public/.commit_hash
	@npx shadow-cljs watch dev

release:
	@git rev-parse HEAD > resources/public/.commit_hash
	@npx shadow-cljs release prod

clean:
	@rm -rf resources/public/assets/

clj-kondo:
	@clj-kondo --config .clj-kondo/config.edn --lint  src/
	@clj-kondo --config .clj-kondo/config.edn --lint test/

cloc:
	@cloc . --exclude-list-file=cloc.excluded
