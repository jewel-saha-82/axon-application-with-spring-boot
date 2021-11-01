# axon-application

Resources
1. https://www.youtube.com/watch?v=sthMcMrspCM - Shabbir (Followed this for the project)
2. https://www.kindsonthegenius.com/microservices/cqrs-with-axon-tutorial-part-1-introduction-and-setup-in-intellij/  - Point 2 is with this.
3. https://www.youtube.com/playlist?list=PL9l1zUfnZkZl7PEv8955O3Cg018e1qrFV
4. https://www.youtube.com/watch?v=SL2VSYecDvQ
5. DDD - https://www.youtube.com/playlist?list=PLZBNtT95PIW3BPNYF5pYOi4MJjg_boXCG
6. https://www.youtube.com/watch?v=tqn9p8Duy54 - Spring Boot
7. https://www.youtube.com/watch?v=Jp-rW-XOYzA - Webiner

# https://stackoverflow.com/questions/51879692/events-from-axondb-eventstore-do-not-arrive-in-my-trackingeventprocessor
You mentioned that you have two different applications. Do you have a common module between those applications which shares the core API (commands, events, and queries)? If by any chance that is not the case, does the fully qualified name of your events matches (one in the publishing application and one the consuming application)? If the fully qualified name of events does not match, tracking event processor will not be able to pick it up.
