<h1>Project based Assignment specification</h1>

1. Copy the Project01 and paste it in your Theory/Assignment/Assignment02/ folder in the Git repository. Rename the project to Project02. Follow the project rename instructions for android.
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
		iv. Second tab should contain a listview of university affiliation fragments

	c. Third tab contains phone numbers for the members. Students can add multiple phone numbers using a floating action button.
	
	i. Phone number should contain the following information:
		1. Tag (Home/office/Other/Custom)
		2. Phone number
	ii. Each phone number entry should be inside a fragment.
	iii, When a fab button is clicked, another phone number fragment is added to the third tab.
	iv. Third tab should contain a listview of phone numbers.

3. Add a submit button in lateral navigation activity. On clicking the submit button, add the member information to a recyclerview. Each entry in the recyclerview contains NID and person name. On tapping an entry in the recyclerview, display the information in a dialogue message. 

	Use file serialization for saving the data in the back end. Send data from one activity to another using parcelable.
	
<h5>1. First Fragment</h5>

![Screenshot_2021-08-21-01-58-58-773_com istiaksaif Project01](https://user-images.githubusercontent.com/74167381/130288150-4e88b413-45d1-4a29-8042-661782f604e8.jpg)


<h5>Second Fragment</h5>

![Screenshot_2021-08-21-01-59-02-617_com istiaksaif Project01](https://user-images.githubusercontent.com/74167381/130288212-69264e74-1d3c-4457-8d0a-803a1d1888c3.jpg)

<h6>After Fab button click </h6>

![Screenshot_2021-08-21-01-59-06-001_com istiaksaif Project01](https://user-images.githubusercontent.com/74167381/130288329-de795a65-b2f4-4ac9-8b71-3d4fad1d6f56.jpg)


<h5>Third Fragment </h5>

![Screenshot_2021-08-21-01-59-12-188_com istiaksaif Project01](https://user-images.githubusercontent.com/74167381/130288367-bb3a6353-1b51-4369-bee1-ab52147f4c97.jpg)

<h5>Final Activity UserList</h5>

![Screenshot_2021-08-21-01-59-51-400_com istiaksaif Project01](https://user-images.githubusercontent.com/74167381/130288421-d78e112e-c2d2-4377-be27-92a158597827.jpg)

<h5>List Item Click and Open Popup show details </h5>

![Screenshot_2021-08-21-01-59-53-810_com istiaksaif Project01](https://user-images.githubusercontent.com/74167381/130288484-636ac5cf-2bdb-46d3-9842-0e4c3a0bea96.jpg)


