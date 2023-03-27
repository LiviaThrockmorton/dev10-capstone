# Capstone

## 1. Problem Statement

>Dressing up is a form of imaginative play — and imaginative play boosts problem-solving and self-regulation skills. Kids create situations and scenes and act out social events. They're able to test out new ideas and behaviors in a comfortable environment. Dress-up encourages creative thinking and communication skills.

>https://www.healthline.com/health/childrens-health/playing-dress-up#benefits

Playing imaginitive games online can bring more variety to a kid's play, and help them become familiar with using a computer in a fun way.  Online games can be more accessible than physical play depending on a person's situation.

## 2. Technical Solution

Create an application for dressing up a character, and allow users to post outfits and get inspiration from others.

### Scenario 1
Sara is tired of playing dress-up by herself.  She wants to play with new clothes and show her outfits to her friends.  Her parents don't want her to use social media where she could be easily identified, because she is still young.  She plays Dress-Up Duck and tries new clothes on a new character.  She gets to be creative in a new way, in a safe environment.  She can post her outfits and communicate with others about their outfits.

### Scenario 2
Hance likes playing computer games and seeing different art styles.  He especially likes games with animals.  He plays Dress-Up Duck to see how it compares to other online games, and decides to keep his outfits to himself because he doesn't feel like reading comments or making comments.

## 3. Glossary

### Duck
A character that can be dressed up.  A user can choose from multiple characters before creating an outfit.
### Outfit
A collection of a duck and clothing items.  An outfit requires at least one clothing item, but no more than three.  Outfits can be saved by a user that is signed-in, and once a user saves an oufit they can post it if they choose.
### Clothing Item
Hats, shirts, and bottoms that can be worn by a duck.  Clothing items can be combined in multiple ways, but there can only be one of each kind.
### Post
An outfit that is made public for anyone to see.  Comments can be made on posts.
### Comment
Text that can be added by a user to another user's post.  Admin can delete comments.
### User
Read-only, registered, and admin are different user-roles.  Certain roles have certain permissions.

## 4. High Level Requirements

- View posts (anyone).
- Create an outfit (MEMBER, ADMIN).
- Edit an outfit (MEMBER, ADMIN).
- Post an outfit (MEMBER, ADMIN).
- Delete (hide) an outfit (MEMBER, ADMIN).
- Take down a post (MEMBER, ADMIN).
- Delete a post (ADMIN).
- Comment on a post (MEMBER, ADMIN).
- Delete a comment (ADMIN).

## 5. User Stories/Scenarios

As a casual user, I want to view all outfits so I can see what I can incorporate in my own personal style.

As an authenticated user, I want to create an outfit so I can save it for future viewing.

As an administrator, I want to have the option to modify the Forum so we can have a clean, fun experience for all.

### Create an outfit
- Create an outfit that users can save.
- Suggested data:
    - Duck (character)
    - Shirt, pants, hat (outfit has to include *at least* one clothing item)
    - outfitId (an outfitId is needed, because we’ll need to get that Id for the Forum)
    - userId (obvious need)
- Precondition: User must be logged in with the “signed in”/“registered user” role
- Post-condition: If the user is “registered”, the outfit can be saved.

### Delete an outfit
- Delete an outfit of your own that had been saved.
- Precondition: User must be logged in with the “signed in”/“registered user” role OR If user is logged in as “ADMIN”, any outfit can be deleted.
- Post-condition: Data is not deleted, just hidden just in case the user changes their mind. *VISIBLE TO THE ADMIN*

### Add comment to Forum
- The Forum is a place for other “registered users” to share their outfits with the community
- Suggested data:
    - commentId
    - userId
    - outfitId
    - dateTime (to show when a comment was posted)
- Precondition: Precondition: User must be logged in with the “signed in”/“registered user” role
- Post-condition: If the user is “registered”, the comment will be saved and posted to the Forum

### Delete comment from Forum
- The Forum is a place for other “registered users” to share their outfits with the community
- Suggested data:
    - commentId
    - userId
    - outfitId
    - dateTime (to show when a comment was posted)
- Precondition: User must be logged in with the “signed in”/“registered user” role OR If user is logged in as “ADMIN”, any comment from any user can be deleted.
- Post-condition: If the user is “registered”, the comment will be deleted. 

### Browse Outfits
- How to display the outfits:
    - Outfits saved will be posted in the Forum 
    - If the user is NOT “registered” or “ADMIN”, they can only view the Forum and scroll through the outfits saved.
