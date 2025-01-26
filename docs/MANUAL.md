# How to use telsos-reagent as an upstream

The following instruction assumes we want to create a new front-end single-page application called *\<app-name\>*.



1. Go to GitHub and create a new empty repository, https://github.com/\<github-username\>/\<app-name\>



2. Clone your repo

   ```bash
   git clone https://github.com/<github-username>/<app-name>
   cd <app-name>
   ```



3. Add telsos-reagent repository as your upstream:

   ```bash
   git remote add upstream https://github.com/kongra/telsos-reagent
   ```



4. Pull upstream

   ```bash
   git pull upstream master
   ```

   or

   ```bash
   git rebase upstream/master
   ```



   Next:

   ```bash
   git status
   On branch master
   Your branch is based on 'origin/master', but the upstream is gone.
     (use "git branch --unset-upstream" to fixup)

   nothing to commit, working tree clean
   ```

   So you need to:

   ```bash
   git branch --unset-upstream
   ```

   and then

   ```bash
   git status
   On branch master
   nothing to commit, working tree clean
   ```

   Finally:

   ```bash
   git push -u origin master
   ```



5. Apply changes to your new \<app-name\>. As of writing this I had to affect the following files:

   ```bash
   README.md
   ```



6. Execute

   ```bash
   make watch-app
   make watch-css
   make clean
   make release
   ```




7. In order to pull changes from the upstream to this service:

   ```bash
   git fetch upstream
   git rebase upstream/master
   ```
