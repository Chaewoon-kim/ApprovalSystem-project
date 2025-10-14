
# 전자결재 시스템

#### 커밋 컨벤션
- Commit Message
    - Type : Subject
- Type(이모지 사용 지향)
    - feat: 새로운 기능 추가
    - fix: 버그 수정
    - refactor: 버그 수정이나 기능 추가 없이 코드 구조만을 개선
    - test: 테스트 코드 추가
    - docs: 문서만 변경 시
    - delete: 코드/파일 삭제
- Subject
    - 제목은 간결하게 요점 내용을 작성
    - 서술형 문장 지양

- Example Message
    - feat: "회원 가입 기능 구현"
    - fix: 로그인 세션 만료 오류 수정

#### 브랜치 전략
- 개발자 단위 브랜치 전략
- 각자 독립적으로 개발하는 개인 브랜치 사용
- 각자의 브랜치에 여러 기능을 동시에 작업

#### 기본 운영 규칙
- 각자 브랜치에서 자유롭게 개발
- main 브랜치에 직접 push 금지
- 코드 에러 발생 시 해결 후에만 Commit 및 PR 가능 

#### 병합 및 PR 규칙
- 일정 시점마다 main 브랜치에 Pull Request
- 단위 테스트 시 이상 없는 경우에만 Pull Request
- 코드 리뷰 승인 후 main에  merge

#### PR 작성 규칙
- Title: 간결하고 요점만 작성
- Description: PR의 목적과 내용을 상세하게 작성
- Reviewer: 코드 검토자 설정


#### 기본 반복 사용 명령
- git switch [브랜치명]
- git pull origin [브랜치명]
- git add .
- git commit -m "[커밋메시지]"
- git push origin [브랜치명]
- git status