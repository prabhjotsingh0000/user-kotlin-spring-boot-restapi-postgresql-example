-- V2__Add_Is_Logged_In_Column.sql

ALTER TABLE app_user
ADD COLUMN is_logged_in BOOLEAN DEFAULT FALSE;
