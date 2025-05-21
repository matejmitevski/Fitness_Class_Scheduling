FITNESS CLASS SCHEDULING API

INSTRUCTOR ENDPOINTS
POST /instructors
GET /instructors

FITNESS CLASS ENDPOINTS
POST /classes
GET /classes
PATCH /classes/{class_id}

ATTENDEES ENDPOINTS
POST /classes/{class_id}/attendees
POST /classes/{class_id}/attendees/bulk
GET /classes/{class_id}/attendees
DELETE /classes/{class_id}/attendees/{attendee_id}

CANCEL CLASS
PATCH /classes/{class_id}/cancel

SUMMARY of CLASS
GET /classes/{class_id}/summary

WAITLIST ENDPOINTS
POST /classes/{class_id}/waitlist
GET /classes/{class_id}/waitlist

X-API-KEY = 123456


