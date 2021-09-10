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

<h5>1. Main activity</h5>

![Screenshot_2021-07-09-23-14-38](https://user-images.githubusercontent.com/74167381/125170260-f16f1180-e1cf-11eb-8697-b9c1a4637723.png)

<h5>Date Picker</h5>

![Screenshot_2021-07-13-18-37-35](https://user-images.githubusercontent.com/74167381/125456445-4012e140-ddc2-4f73-8d7b-d6c0f52966b7.png)
<h5>2.UniversityAffiliationActivity</h5>

![Screenshot_2021-07-09-23-14-28](https://user-images.githubusercontent.com/74167381/125170263-f3d16b80-e1cf-11eb-9757-5200908f6e52.png)
<h5>3.SecondActivity</h5>

![Screenshot_2021-07-09-23-14-20](https://user-images.githubusercontent.com/74167381/125170265-f5029880-e1cf-11eb-99ec-1cfc9a46c89f.png)
<h5>4.FianlActivity</h5>

![Screenshot_2021-07-11-11-48-16](https://user-images.githubusercontent.com/74167381/125184337-9e807300-e23e-11eb-9de9-43690e40babe.png)

