# FastCampus_Part2

# Chapter01 - BMI 계산기

## 이 챕터를 통해 배우는 것

- **Layout** 을 그리는 법
  - **LinearLayout** 사용하기
  - **TextView** 의 속성들과 사용하는 법
  - **EditText** 의 속성들과 사용하는 법
  - **Button** 사용하는 법
- **Layout** 과 **Kotlin** 코드를 함께 사용하기
- **버그** 수정해보기
- **Activity** 에 대해 알아보기



### Kotlin 문법

when 분기문

람다함수



### BMI 계산하기

공식 =  몸무게(kg) / (키 (m) * 키 (m)) 

18.5 이하면 저체중

18.5 ~ 23 은 정상체중

23 ~ 25는 과체중

25 ~ 30 은 경도 비만

30 ~ 35 는 중정도비만

35 이상은 고도 비만



# Chapter02 - 로또 번호 추첨기

## 이 챕터를 통해 배우는 것

- **Layout** 을 그리는 법
  - **ConstraintLayout** 사용하기
  - **NumberPicker** 의 속성들과 사용하는 법
  - **TextView** 의 속성들과 사용하는 법 (복습)
  - **Button** 사용하는 법 (복습)
- **Layout** 과 **Kotlin** 코드를 함께 사용하기
- **버그** 수정해보기
- 중복 코드 정리하기
- Shape Drawable 사용하기 
  - (https://developer.android.com/guide/topics/resources/drawable-resource#Shape)



### Kotlin 문법

apply

When

Random

Collection

​	- Set, List

람다함수



### 로또 번호 추첨기

0~5개까지 수동 선택 가능하도록 구현하기

수동선택한 번호를 제외한 나머지 번호는 랜덤으로 표시하기




# Chapter03 - 비밀 다이어리

## 이 챕터를 통해 배우는 것

- **Layout** 을 그리는 법
  - **ConstraintLayout** 사용하기 (2)
  - Custom Font 사용하기
- **Handler** 사용하기
- **SharedPreference** 의 속성들과 사용하는 법
- **Theme** 사용하기
- **AlertDialog** 사용하기



### Kotlin 문법

android-ktx 로 SharedPreference 사용하기 (Kotlin Android Extension)



### 비밀 다이어리

다이어리 처럼 UI 꾸며보기

비밀번호를 저장하는 기능, 변경하는 기능

다이어리 내용을 앱이 종료되더라도 기기에 저장하는 기능




# Chapter04 - 계산기

## 이 챕터를 통해 배우는 것

- **Layout** 을 그리는 법

  - **TableLayout** 사용하기
  - **ConstraintLayout** 사용하기
  - **LayoutInflater** 사용하기

- **Thread** 사용하기

  - 타 Thread 만들어서 사용하기

  - **runOnUiThread** 사용하기

- **Room** 사용하기



### Kotlin 문법

확장 함수 사용하기

data class 사용하기



### 계산기

계산기 기능 수행

계산 기록 저장하기

계산 기록 삭제하기

~~단 시간관계 상 정수형으로 한정하고, 몇 가지 기능을 배제하고, 연산자는 1회만 사용할 수 있음~~


# Chapter06 - 뽀모도로 타이머
- CountDownTimer
- SoundPool
- SeekBar 



# Chapter07 - 녹음기
- Request runtime permissions
- CustomView
- MediaRecorder
- MediaPlayer