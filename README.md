The project with basic CRUD and using ViewPager, RecyclerView, Fragment, OptionMenu, SQLiteDBHelper to manage the UI and flow code

## The object
- Wine
	- int id
	- String name
	- int alcoholContent
	- int age
	- OriginalCountry originalCountry
	- ImageView imageView
	
- OriginalCountry
	- int id
	- String name
	- String description


## Open the app
Open the app, the main appears with list of Wine object are managed by Recycle View

Under the app is 2 icon to switch between Wine and OriginalCountry using BottomNavigation

## CRUD
### Create
There is one circle button using Floating Action Button that create Object

### Read
If you click the item of Object except the button Update, you will see the detail of the object using setItemOnClickListener

### Update
There is a button on the left in every item in RecyclerView

### Delete
If you wanna delete any item, just swipe it on the right or left depend which side you are using ItemTouchHelper
