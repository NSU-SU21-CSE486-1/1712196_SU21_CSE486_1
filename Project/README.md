<h1>Project Name: UniCluBz</h1>

<h1>Introduction</h1>

<p>Build an application called UniCluBz that serves as a common platform for all clubs across all universities in Bangladesh. Any club in any university will create a profile for their club. To register any club, the contact information for that club (Email, phone, Web address, Logo, University) will have to be provided. When a club is created, a key person will be associated with the club account.</p>

<h3><strong>Features</strong></h3>

Sign In
![Screenshot_2021-09-17-19-15-12-245_com istiaksaif uniclubz](https://user-images.githubusercontent.com/74167381/133792291-be997603-3b9a-4816-9d83-93227d210c4e.jpg)

Sign Up
![Screenshot_2021-09-17-19-15-16-036_com istiaksaif uniclubz](https://user-images.githubusercontent.com/74167381/133792344-2f57f886-31bb-49aa-b1ce-df7a8deb5622.jpg)

Forget password
If any User Forgot their Passward ,then user can reset passward from there .An email will be sent to the user to the reset there password.
![Screenshot_2021-09-17-19-15-20-668_com istiaksaif uniclubz](https://user-images.githubusercontent.com/74167381/133792456-77b6bd22-9ea2-4633-a575-683b4292928a.jpg)

User Clubs List
![Screenshot_2021-09-17-19-16-41-029_com istiaksaif uniclubz](https://user-images.githubusercontent.com/74167381/133801665-9bbe79f5-ff48-4498-88b2-c9118df61d1a.jpg)

Blood Donate Request(Club Manager/Admin)
![Screenshot_2021-09-17-19-16-55-240_com istiaksaif uniclubz](https://user-images.githubusercontent.com/74167381/133802121-3a232f01-6b1e-433b-84a9-edcd524bc33c.jpg)

Create Event(Club Manager/Admin)
![Screenshot_2021-09-17-19-16-57-735_com istiaksaif uniclubz](https://user-images.githubusercontent.com/74167381/133802503-738d8293-3726-4e65-bae1-aec5bd7bfef7.jpg)

<h2>Data Managment</h2>

<p><h4>Firebase</h4>For this app, I'm using Firebase Database, Storage and Firebase Auth.The user/student information,club information, all event data, blood donate request data, chats are managed by via Firebase Database.Firebase storage are using for store all kind of images.</p>
FireBase Authentication

![Screenshot from 2021-09-17 19-23-41](https://user-images.githubusercontent.com/74167381/133803382-177cf804-dc83-4cf4-89c5-86d35d3216da.png)

FireBase Storage
![Screenshot from 2021-09-17 19-24-04](https://user-images.githubusercontent.com/74167381/133803468-901c4210-e0ca-4105-afbe-3e4199e09884.png)

<strong>FireBase DataBase</strong>
![Screenshot from 2021-09-17 19-20-15](https://user-images.githubusercontent.com/74167381/133803632-8b02a845-5b8a-4a16-be88-01d3eecba586.png)
User data
![Screenshot from 2021-09-17 19-20-51](https://user-images.githubusercontent.com/74167381/133803654-12ab16f7-1e4e-452c-b088-262b94cbe9e6.png)
Event data
![Screenshot from 2021-09-17 19-21-10](https://user-images.githubusercontent.com/74167381/133803670-db5467db-d5c9-48b0-88ff-c7bcb24aa9da.png)
Club Info Data
![Screenshot from 2021-09-17 19-22-52](https://user-images.githubusercontent.com/74167381/133803679-96e08064-4ce9-44c5-a05d-02c7270143fa.png)
<strong>Chats</strong>
![Screenshot from 2021-09-17 19-23-06](https://user-images.githubusercontent.com/74167381/133803686-4a850b71-2a21-4b20-a033-b686600c0bcd.png)
![Screenshot from 2021-09-17 19-23-17](https://user-images.githubusercontent.com/74167381/133803692-94f8e76f-a5d4-48ea-8e64-6616baf3f3a1.png)
BloodDonate Data
![Screenshot from 2021-09-17 19-23-25](https://user-images.githubusercontent.com/74167381/133803703-414f70d7-fa99-4df2-a6ea-0542d4fd932b.png)

<h2>Desing Pattern</h2>
For this app, I'm using MVVM pattern for SignIn SignUp. But due to a lack of time & experiance I couldn't implement exactly like MVVM rest of the other features but it is highly matches with the Observer design pattern of the MVC architecture.

![Screenshot from 2021-09-17 19-19-34](https://user-images.githubusercontent.com/74167381/133807239-1411b066-ad1c-446e-9a12-1b457351ac84.png)
![Screenshot from 2021-09-17 19-20-01](https://user-images.githubusercontent.com/74167381/133807254-f47ec09f-0021-441a-82f2-ee644634ed82.png)







