<p style="text-align: center;">&nbsp;</p>
<p style="text-align: center;">&nbsp;</p>
<p align="center"><strong><img src="https://media.dhakatribune.com/uploads/2016/11/nsulogo.jpg" alt="" width="307" height="172" /></strong></p>



<p align="center"><strong>North South University</strong></p>
<p align="center">Department of Electrical &amp; Computer Engineering</p>
<p align="center"><strong>Project: UniCluBz Mobile App</strong></p>
<p align="center"><strong>Summer 2021 </strong></p>


<p align="center"><strong>Course Name</strong>: Mobile and Wireless Application Development </p>
<p align="center"><strong>Course No</strong>: CSE 486 <strong>Sec</strong><strong>:</strong> 01</p>
<p align="center"><strong>Faculty</strong>: Shaikh Shawon Arefin Shimon (SAS3)</p>
<p align="center"><strong><u>Member 1</u></strong><u>:</u></p>
<p align="center"><strong>Name</strong><strong>:</strong> Istiak Ahamed Saif</p>
<p align="center"><strong>ID</strong><strong>:&nbsp; </strong>1712196</p>
<p align="center"><strong>Email</strong><strong>:</strong> <a href="mailto:istiak.saif@northsouth.edu">istiak.saif@northsouth.edu</a></p>

<p align="center"><strong>Git Repository</strong><strong>: </strong><a href="https://github.com/NSU-SU21-CSE486-1/1712196_SU21_CSE486_1">https://github.com/NSU-SU21-CSE486-1/1712196_SU21_CSE486_1</a></p>

<p align="center"><strong>Date Prepared</strong><strong>: </strong>July 10, 2021</p>
<p><strong>&nbsp;</strong></p>
<p><strong>&nbsp;</strong></p>


--------------------------------------------------------------------------------------------

<p>Build an application called UniCluBz that serves as a common platform for all clubs across all universities in Bangladesh. Any club in any university will create a profile for their club. To register any club, the contact information for that club (Email, phone, Web address, Logo, University) will have to be provided. When a club is created, a key person will be associated with the club account. </p>

<p>General users will create user profiles specifying their University, university-provided email (optional), Student ID, Department, study level, (undergraduate/MS/PhD/ Post-Doc), Date of Birth, Phone number,Email , NID, Blood group and Name. One user can have association with multiple universities (undergraduate student in one, graduate student in another) as long as they can verify university emails associated with each account. One user can have multiple phone numbers or email addresses.</p>

<p>Once general user profiles are created, a club can add a user as it’s member (or officer) as long as the user has any association with the university the club is affiliated with.</p> 

<p>Any club will at least have one admin member. It can have multiple admins. Each user with admin privileges will be able to log onto the club view and create events or blood donation requests.</p>

<strong>Features:</strong>

<strong>A)	Events:</strong> Once a club profile is created, clubs can create and share events from their page. Events can be of three types - 

    1. Public (any user from any university can register),
    2. University-only (any user from a specified number or numbers of universities can join), 
    3. Private (only club members can view the events). Any club member can join. 

<p>Users will be able to see events in their newsfeed for which they are eligible for. Once a user sees an event, he or she will be able to do a one-click registration for the event. Once registered, the club will receive the user details, and the user and the club will both receive a unique id for this event against this user.</p>

This unique registration id can be put in a search box in the app to pull up the registration details for the user in this event.

<strong>B) Blood donation request:</strong>

  Similar to Events, the clubs will also be able to register blood donation requests. A blood donation request will include the following information:

    i. Patient Name
    ii. Hospital
    iii. Hospital Location
    iv. Contact person Name
    v. Contact person phone number
    vi. Required blood group
    vii. Date and time needed.

A user registered in the portal will be able to see all blood donation requests that match the user’s blood group. If the user accepts the blood donation request, the user can see these details in his dashboard, and the club will receive a notification of a donation request acceptance.

There should be one single app for managing all club and user activity. There should be different workflows for different types of users (club / general user). 

<strong>C) Handle incoming join request:</strong>

<strong>Required Specifications:</strong>

    i. Preferred Application Architecture - MVVM (Model - View - View Model). Look at application architecture defined in Jetpack Architecture Components. 
    ii. Use Jetpack libraries when possible.
    iii. Database: Firebase. SQL database inside mobile is not allowed
    iv. Authentication: Firebase.
