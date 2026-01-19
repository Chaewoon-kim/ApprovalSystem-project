## 📄 전자결재 시스템 - Easy
<img width="402" height="79" alt="스크린샷 2026-01-19 150859" src="https://github.com/user-attachments/assets/ec235c3a-8b21-44b6-af1a-92aaaa170cc3" />


## 🚩 서비스 목적
최근 기업에서 많이 활용되는 **그룹웨어의 핵심인 전자결재시스템**을 직접 구현합니다.<br/>
복잡한 비즈니스 로직과 결재 승인 프로세스를 깊이 있게 이해하고 데이터 무결성을 보장하는 시스템 구축을 목표로 합니다.

## 🚩 프로젝트 기간 
**2025.09.22 ~ 2025.10.17(3주)**

## 🚩 팀 소개
| 김채운[팀장] | 배성윤 | 성민서 | 지연우 |
|:------:|:------:|:------:|:------:|
| <img src="https://avatars.githubusercontent.com/u/149470542?v=4" alt="김채" width="150"> | <img src="https://avatars.githubusercontent.com/u/58555356?v=4" alt="배성윤" width="170"> |<img src="https://avatars.githubusercontent.com/u/160600045?v=4" alt="성민서" width="170"> |<img src="https://avatars.githubusercontent.com/u/110551002?v=4" alt="지연우" width="150"> |
| [@Chaewoon-kim](https://github.com/Chaewoon-kim) | [@tjddbs2065](https://github.com/tjddbs2065) | [@minse00](https://github.com/minse00) | [@wldusdn](https://github.com/wldusdn) |

## 🚩 주요 기능 
- **스마트 기안서 작성**: 문서 양식별 기본 결재선 자동 로드 및 사용자 지정 결재선 설정 기능
- **실시간 모니터링**: 문서별 결재 진행 상태 및 전체 결재 히스토리 시각화
- **유연한 권한 관리**: 결재자 부재 시 대결 처리 및 단계별 프로세스에 따른 동적 권한 부여

## 🚩 Tech Architecture & Design
### Software Architecture
<img width="957" height="460" alt="SW 아키텍처" src="https://github.com/user-attachments/assets/92592cee-a3ee-40e4-8cbb-4a79abb51e3b" />

### ERD
<img width="1517" height="698" alt="erd" src="https://github.com/user-attachments/assets/5bfe9fc2-b643-490c-bde5-da2ac896ae3f" />

### UI/UX Design(Figma)
<a href="https://www.figma.com/design/eQiVduK0wObwpdDftopblq/%EC%A0%84%EC%9E%90%EA%B2%B0%EC%9E%AC%EC%8B%9C%EC%8A%A4%ED%85%9C--%EB%B3%B5%EC%82%AC-?node-id=0-1&t=mnT0qvtRsXl2Y97C-1">**🔗 Figma 디자인 상세보기**</a>
<br />

<img width="1302" height="830" alt="figma" src="https://github.com/user-attachments/assets/9ca12601-8253-4a8e-8621-056e6f4b88bd" />

## 🚩 Development Workflow
#### 1. Commit Convention

* Commit Message
  ```
  Type : Subject
  ```

* Type(이모지 사용 지향)
  * feat: 새로운 기능 추가
  * fix: 버그 수정
  * refactor: 버그 수정이나 기능 추가 없이 코드 구조만을 개선
  * test: 테스트 코드 추가
  * docs: 문서만 변경 시
  * delete: 코드/파일 삭제

* Subject
  * 제목은 간결하게 요점 내용을 작성
  * 서술형 문장 지양
  * 영문으로 작성

* Example Message

  `feat: "회원 가입 기능 구현"`<br/>
  `fix: 로그인 세션 만료 오류 수정`

#### 2. Branch Strategy & Rules
* **브랜치 운영**: 각 개발자 성함을 딴 개별 브랜치에서 독립적으로 개발을 진행합니다.
* **Main 보호**: main 브랜치 직접 push는 엄격히 금지하며, PR을 통해서만 병합합니다.
* **검증 절차**: 로컬 단위 테스트 완료 및 에러 해결이 확인된 코드만 Commit/PR 가능합니다.

#### 3. PR & Merge Policy
* **PR 작성**: 목적과 내용을 상세히 기술하고 Reviewer를 지정합니다.
* **병합 조건**: 지정된 리뷰어의 코드 리뷰 승인(Approve) 후 최종 Merge를 진행합니다.


#### 4. Git Cheat Sheet (팀 공통)
```
git switch [브랜치명]          # 브랜치 이동
git pull origin [브랜치명]     # 최신 코드 동기화
git add .                    # 변경사항 스테이징
git commit -m "Type: Subject" # 커밋 메시지 작성
git push origin [브랜치명]     # 원격 저장소 업로드
```
