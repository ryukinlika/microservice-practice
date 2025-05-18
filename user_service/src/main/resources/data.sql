-- Insert initial data into the user table
INSERT INTO user (id, created_at, email, is_admin, last_login, password, username) VALUES
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), '2025-05-18 10:00:00', 'a@example.com', b'1', '2025-05-17 09:00:00', 'a', 'a'),
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440001', '-', '')), '2025-05-18 11:00:00', 'b@example.com', b'0', '2025-05-17 10:00:00', 'b', 'b'),
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', '')), '2025-05-18 12:00:00', 'c@example.com', b'0', '2025-05-17 11:00:00', 'c', 'c'),
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), '2025-05-18 13:00:00', 'd@example.com', b'1', '2025-05-17 12:00:00', 'd', 'd'),
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440004', '-', '')), '2025-05-18 14:00:00', 'e@example.com', b'0', '2025-05-17 13:00:00', 'e', 'e'),
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440005', '-', '')), '2025-05-18 15:00:00', 'f@example.com', b'0', '2025-05-17 14:00:00', 'f', 'f'),
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', '')), '2025-05-18 16:00:00', 'g@example.com', b'1', '2025-05-17 15:00:00', 'g', 'g');

-- Insert relationships into the relationship table
INSERT INTO relationship (follower_id, followed_id) VALUES
    -- a follows b, c, d, e, f, g
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440001', '-', ''))), -- a -> b
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', ''))), -- a -> c
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', ''))), -- a -> d
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440004', '-', ''))), -- a -> e
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440005', '-', ''))), -- a -> f
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440006', '-', ''))), -- a -> g

    -- b follows a
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440001', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', ''))), -- b -> a

    -- c follows a, b, c, d
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', ''))), -- c -> a
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440001', '-', ''))), -- c -> b
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', ''))), -- c -> c
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440002', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', ''))), -- c -> d

    -- d follows e, f
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440004', '-', ''))), -- d -> e
    (UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440003', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440005', '-', ''))); -- d -> f