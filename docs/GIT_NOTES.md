# Git and GitHub Notes

## 1. Why is it called version control?

Git is not only code storage. Git records every meaningful saved state of a project as a **version** (a commit).

```text
Version 1: Initial project
Version 2: Add login page
Version 3: Fix login validation
Version 4: Add JWT security
```

With Git, you can compare versions, see who changed a line, return to an older working version, work on features separately, and combine work from multiple people. This is why it is called a **Version Control System (VCS)**.

GitHub is online hosting and collaboration for Git repositories. Git works on the laptop even without GitHub or the internet.

| Tool | Purpose |
| --- | --- |
| Git | Tracks local project history and versions. |
| GitHub | Stores Git repositories online and supports collaboration. |
| Commit | A named snapshot/version of selected files. |
| Branch | A separate line of work, such as `feature/login`. |
| Remote | A saved connection to an online repository. |
| `origin` | The usual nickname for the primary GitHub remote. |

## 2. The Git workflow

```text
Edit files
    ↓
git add
    ↓
Staging area (choose files for the next version)
    ↓
git commit
    ↓
Local Git history on this laptop
    ↓
git push
    ↓
GitHub repository
```

`git add` does not upload code. `git commit` does not upload code. Only `git push` uploads committed history to GitHub.

## 3. One-time Git setup on Windows

Install Git for Windows from <https://git-scm.com/install/windows>.

Open a new PowerShell window and verify it:

```powershell
git --version
```

Set the author information used on future commits:

```powershell
git config --global user.name "Your GitHub username"
git config --global user.email "your-github-email@example.com"
```

Check settings:

```powershell
git config --global --list
```

`user.name` identifies the commit author. It does **not** grant GitHub push permission. GitHub permission comes from the account used by Git Credential Manager and repository access settings.

## 4. Git Credential Manager (GCM)

Git for Windows includes Git Credential Manager. It opens a browser for GitHub login and securely saves the credential in Windows Credential Manager.

Check it:

```powershell
git credential-manager --version
```

The first private `git pull` or `git push` normally opens a browser. Sign in with the GitHub account that owns the repository or has collaborator/write permission.

If Git uses the wrong account:

1. Open **Credential Manager** from Windows Search.
2. Choose **Windows Credentials**.
3. Remove the `git:https://github.com` credential.
4. Run `git push` again and sign in with the correct GitHub account.

Never paste a GitHub password, personal access token, API key, or database password into source code or chat.

## 5. New project: first push to GitHub

Create a repository on GitHub first. For the easiest first push, create it **empty**: do not add a README, `.gitignore`, or license on GitHub yet.

Then run these commands in the new project folder:

```powershell
cd D:\projects\my-new-project

git init
git branch -M main
git status

git add .
git commit -m "Initial commit"

git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
git push -u origin main
```

### What each command means

| Command | Meaning |
| --- | --- |
| `cd D:\projects\my-new-project` | Selects the folder/project that Git commands will affect. |
| `git init` | Creates a hidden `.git` folder; this local folder becomes a Git repository. |
| `git branch -M main` | Names the local default branch `main`. It does not create a GitHub branch by itself. |
| `git status` | Shows branch, changed files, staged files, and useful next actions. |
| `git add .` | Stages non-ignored file changes for the next commit. |
| `git commit -m "..."` | Creates a local version/snapshot with a message. |
| `git remote add origin URL` | Saves the GitHub URL under the nickname `origin` for this project only. |
| `git push -u origin main` | Uploads local `main`; `-u` remembers its GitHub tracking branch. |

Each project has its own hidden `.git` folder, so it has its own remote URL and history:

```text
D:\projects\afi-arena\.git  → https://github.com/afrididevtech/afiarena.git
D:\projects\portfolio\.git  → https://github.com/Afridi0505/portfolio.git
```

Use this to view a project's remote:

```powershell
git remote -v
```

After the first `git push -u`, normal pushes and pulls only need:

```powershell
git push
git pull
```

## 6. Daily solo-project flow

Before starting work:

```powershell
git pull
```

After changes:

```powershell
git status
git add .
git commit -m "Add player registration validation"
git push
```

Use short, meaningful commit messages. A commit should represent one logical change.

## 7. Team workflow with branches

Do not directly change `main` for every feature in a shared project.

```powershell
git pull
git checkout -b feature/login-page

# edit code
git add .
git commit -m "Add login page"
git push -u origin feature/login-page
```

On GitHub, create a Pull Request from `feature/login-page` to `main`. After review, merge it.

For later changes on the same branch:

```powershell
git add .
git commit -m "Fix login validation"
git push
```

## 8. Pull, merge, and rebase

Suppose GitHub has a teammate's commit `B` and the laptop has your commit `C`:

```text
GitHub: A → B
Laptop: A → C
```

### `git pull`

`git pull` downloads the latest remote changes and merges them into the current branch.

### `git pull --rebase`

Rebase downloads `B`, temporarily removes your `C`, then replays your work after `B`:

```text
A → B → C'
```

`C'` contains your change but sits after the newest remote change. Rebase keeps history more linear.

### Conflicts

Git can merge different files automatically. It cannot decide when two people change the same lines. It stops and shows both versions:

```java
<<<<<<< HEAD
String title = "Remote version";
=======
String title = "My local version";
>>>>>>> my-commit
```

You choose the correct final code. You may keep remote code, keep local code, or combine both. Delete all three marker lines (`<<<<<<<`, `=======`, and `>>>>>>>`).

For a rebase conflict:

```powershell
git status
# edit the conflicted file
git add filename
git rebase --continue
```

To cancel the complete rebase safely:

```powershell
git rebase --abort
```

For a merge conflict, resolve the file, then:

```powershell
git add filename
git commit
```

## 9. Important checks and fixes

```powershell
git status                 # current changes and current branch
git log --oneline -5       # last five versions
git branch -vv             # branches and their tracking remote
git remote -v              # GitHub URL for this project
git diff                   # unstaged changes
```

| Error | Cause | Usual fix |
| --- | --- | --- |
| `403 Permission denied` | Logged-in GitHub account lacks write access. | Sign in with owner account or request collaborator/write access. |
| `no tracking information` | Local branch has no remote branch connection. | `git push -u origin main` |
| `origin already exists` | Remote is already saved. | Check with `git remote -v`; change with `git remote set-url origin URL` if necessary. |
| `non-fast-forward` | GitHub has commits you do not have locally. | `git pull --rebase`, resolve conflicts if Git stops, then `git push`. |
| `unrelated histories` | Local and GitHub repositories started separately. | Prefer an empty GitHub repository initially. For an existing case: `git pull --allow-unrelated-histories origin main`. |

## 10. Never commit these files

Use `.gitignore` before the first `git add .`:

```gitignore
node_modules/
dist/
build/
.gradle/
.idea/
.env
application-local.properties
```

Keep passwords, tokens, connection strings, private keys, and `.env` files out of Git. Use environment variables or secret-management tools instead.

## 11. The commands to remember

```powershell
git status
git pull
git add .
git commit -m "Describe the change"
git push
```
