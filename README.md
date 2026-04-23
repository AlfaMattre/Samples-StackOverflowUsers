## What the app does

You open it, it loads a list of StackOverflow users, and you can tap a button on any user card to follow or unfollow them. The app remembers who you followed even after you close it, so your list is still there next time you come back.

---

## Getting it running

No build setup needed. Just grab the APK:

1. Go to the Releases page
2. Download the latest StackOverflowUsers.apk
3. Transfer it to your Android device and install it

or simply clone the repository.

You'll need:
- Android Studio (Hedgehog or newer)
- JDK 17
- A device or emulator running Android 7.0 (API 24) or above

---

## Why I made the choices I did

**SharedPreferences for local storage**

I considered DataStore, but honestly it felt like overkill here. The only thing being saved is a set of integer IDs. SharedPreferences handles that natively with StringSet.

**Flow for the follows list**

The followed set is observed throughout the app. When you tap follow, the change flows from the data layer all the way up to the UI.

**Retrofit + Kotlinx Serialization**

Kotlinx Serialization doesn't use reflection, works well with data class.


