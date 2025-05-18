-- -- Insert initial data into the user table
-- INSERT INTO user (id, created_at, email, is_admin, last_login, password, username) VALUES
--     (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), '2025-05-18 10:00:00', 'a@example.com', b'1', '2025-05-17 09:00:00', 'a', 'a'),
--     (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440001', '-', '')), '2025-05-18 11:00:00', 'b@example.com', b'0', '2025-05-17 10:00:00', 'b', 'b'),
--     (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', '')), '2025-05-18 12:00:00', 'c@example.com', b'0', '2025-05-17 11:00:00', 'c', 'c'),
--     (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), '2025-05-18 13:00:00', 'd@example.com', b'1', '2025-05-17 12:00:00', 'd', 'd'),
--     (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440004', '-', '')), '2025-05-18 14:00:00', 'e@example.com', b'0', '2025-05-17 13:00:00', 'e', 'e'),
--     (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440005', '-', '')), '2025-05-18 15:00:00', 'f@example.com', b'0', '2025-05-17 14:00:00', 'f', 'f'),
--     (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), '2025-05-18 16:00:00', 'g@example.com', b'1', '2025-05-17 15:00:00', 'g', 'g');


INSERT INTO posts (id, user_id, text_content, media_ids, created_at) VALUES
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 'a post (1)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440001', '-', '')), 'b post (1)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440001', '-', '')), 'b post (2)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), 'd post (1)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), 'd post (2)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), 'd post (3)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), 'd post (4)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), 'g post (1)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), 'g post (2)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), 'g post (3)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), 'g post (4)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), 'g post (5)', '', NOW(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), 'g post (6)', '', NOW(6));

