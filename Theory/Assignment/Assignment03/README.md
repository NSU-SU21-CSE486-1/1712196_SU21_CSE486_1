<h1>Project based Assignment03 specification</h1>

1. Copy the Project01 and paste it in your Theory/Assignment/Assignment03/ folder in the Git repository. Rename the project to Project03. Follow the project rename instructions for android.
2. Implement lateral navigation (tabbed view) activity for profile implementation page
	
	a. First Tab contains basic information about the members.
		
		i. Name
		ii. Date of Birth
		iii. NID
		iv. Blood Group

	b. Second Tab contains University Affiliation about the members. Students can add multiple university affiliation using a floating action button.
		
		i. University Affiliation should contain the following information:
			1. University name (Use drop down, pre-populated list)
			2. Department (Use drop down, pre-populated list, one for each university)
			3. Student ID
			4. Study Level (undergraduate / MS / PhD / Post-Doc)
			5. Email

		ii. Each university affiliation should be created inside a fragment.
		iii. When a fab button is clicked, another university affiliation fragment is added. 
		iv. Second tab should contain a listview of university affiliation fragments.

	c. Third tab contains phone numbers for the members. Students can add multiple phone numbers using a floating action button.

		i. Phone number should contain the following information:
			1. Tag (Home/office/Other/Custom)
			2. Phone number
		ii. Each phone number entry should be inside a fragment.
		iii. When a fab button is clicked, another phone number fragment is added to the third tab.
		iv. Third tab should contain a listview of phone numbers.


3. Add a submit button in lateral navigation activity. On clicking the submit button, add the member information to a recyclerview. Each entry in the recyclerview contains NID and person name. On tapping an entry in the recyclerview, display the information in a dialogue message. 

Use Room SQL database for saving the data in the back end. Send data from one activity to another using parcelable. 

<h5>1. First Tab (Student personal info)</h5>

![Screenshot_2021-09-11-00-24-10-594_com istiaksaif Project03](https://user-images.githubusercontent.com/74167381/132900676-7680faa7-f0fd-48b4-93df-4cfdc178ee51.jpg)


<h5>Date Picker</h5>

![Screenshot_2021-07-13-18-37-35](https://user-images.githubusercontent.com/74167381/125456445-4012e140-ddc2-4f73-8d7b-d6c0f52966b7.png)
<h5>2. Second tab (UniversityAffiliation)</h5>

![Screenshot_2021-09-11-00-24-03-430_com istiaksaif Project03](https://user-images.githubusercontent.com/74167381/132900732-4b7f93c0-179f-4913-9121-4630634f1cab.jpg)
![Screenshot_2021-09-11-00-24-08-169_com istiaksaif Project03](https://user-images.githubusercontent.com/74167381/132900744-1c78d5eb-f966-46e4-8ea6-2afbf7921090.jpg)

<h5>3.Third Tab (contacts) </h5>

![Screenshot_2021-09-11-00-23-59-746_com istiaksaif Project03](https://user-images.githubusercontent.com/74167381/132900991-db442514-2098-4d6a-89e1-b7171dbbf164.jpg)

<h5>4.FianlActivity</h5>

![Screenshot_2021-09-11-00-24-18-085_com istiaksaif Project03](https://user-images.githubusercontent.com/74167381/132901034-51f4a0c1-b835-479b-8a52-77a208b78f51.jpg)

<h5>4.When clcik card item show popup Dialog</h5>

![Screenshot_2021-09-11-00-24-24-235_com istiaksaif Project03](https://user-images.githubusercontent.com/74167381/132901129-d87952a2-abb2-4334-a11a-149449971063.jpg)

