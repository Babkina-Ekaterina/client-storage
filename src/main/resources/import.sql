INSERT INTO contact_type (name) VALUES ('email') ON CONFLICT (name) DO NOTHING;
INSERT INTO contact_type (name) VALUES ('phoneNumber') ON CONFLICT (name) DO NOTHING;