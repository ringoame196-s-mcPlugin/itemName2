@echo off

REM ユーザーにコミットメッセージを入力させる
set /p commit_message="Enter commit message: "

REM git add, commit, push を実行
git add .
git commit -m "%commit_message%"
if errorlevel 1 (
    echo Error
) else (
    git push
    if errorlevel 1 (
        echo Error
    ) else (
        echo Success
    )
)