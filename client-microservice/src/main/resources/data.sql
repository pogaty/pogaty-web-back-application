
-- Client 101
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (101, 'sample_derek', 'derek0001', 'derek', 'smart', 'male', '0597144231', 'sample_derek.pogaty@gmail.com', '49996 Alexander Plaza, Port Arlenberg, IN 88649-5705', 'Derek is a dedicated physicist with an unassuming yet thoughtful demeanor. His sharp mind and relentless curiosity drive his groundbreaking research, and he known for his passion and willingness to share his discoveries with others. When not in the lab, Derek enjoys stargazing and reading classic science fiction, maintaining his insatiable fascination with the cosmos.', 'sample-1.png');

-- Client 102
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (102, 'sample_raura', 'raura0002', 'raura', 'craft', 'female', '0814326675', 'sample_raura.pogaty@gmail.com', 'Apt. 128 7510 Trantow Terrace, Ezraberg, ME 95760', 'Raura is a renowned expert in artificial intelligence, celebrated for their innovative contributions to the field. Their confident and approachable demeanor complements their dedication to advancing AI technologies. Raura work spans a wide range of applications, from chatbots to autonomous vehicles, all while advocating for ethical AI development. In their free time, Raura enjoys building AI-powered robots, and they are at the forefront of shaping AI future impact on society.', 'sample-2.png');

-- Client 103
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image )
values (103, 'sample_jane', 'jane0003', 'Jane', 'Smith', 'female', '0712345678', 'sample_jane.pogaty@gmail.com', '123 Main Street, Anytown, USA', 'Jane is a talented software engineer with a passion for creating user-friendly applications. Her attention to detail and problem-solving skills make her a valuable asset to any development team. In her spare time, Jane enjoys hiking and volunteering for local non-profit organizations.', 'sample-3.png');

-- Client 104
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (104, 'sample_mike', 'mike0004', 'Mike', 'Johnson', 'male', '0919876543', 'sample_mike.pogaty@gmail.com', '789 Elm Street, Another City, CA', 'Mike is a marketing expert known for his creative and data-driven approach to campaigns. He has a deep understanding of consumer behavior and a track record of successful product launches. Outside of work, Mike is an avid photographer and enjoys traveling to capture unique moments.', 'sample-4.png');

-- Client 105
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (105, 'sample_lisa', 'lisa0005', 'Lisa', 'Brown', 'female', '0812345678', 'sample_lisa.pogaty@gmail.com', '456 Oak Avenue, Smallville, TX', 'Lisa is a dedicated teacher with a passion for inspiring young minds. Her enthusiasm for education and creative teaching methods have earned her the respect of both students and colleagues. In her free time, Lisa enjoys gardening and painting.', 'sample-5.png');

-- Client 106
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (106, 'sample_john', 'john0006', 'John', 'Williams', 'male', '0718765432', 'sample_john.pogaty@gmail.com', '567 Pine Street, Hometown, NY', 'John is a talented graphic designer with a keen eye for aesthetics. His work spans branding, web design, and illustration, and he is known for his ability to bring creative visions to life. Outside of work, John is a music enthusiast and enjoys playing the guitar.', 'sample-6.png');

-- Client 107
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (107, 'sample_emily', 'emily0007', 'Emily', 'Davis', 'female', '0912345678', 'sample_emily.pogaty@gmail.com', '789 Cedar Street, Riverside, FL', 'Emily is a skilled healthcare professional with a compassionate and caring nature. She has dedicated her career to improving the well-being of patients and is known for her outstanding patient care. In her free time, Emily enjoys yoga and volunteering at local health clinics.', 'sample-7.png');

-- Client 108
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (108, 'sample_david', 'david0008', 'David', 'Martinez', 'male', '0712345678', 'sample_david.pogaty@gmail.com', '123 Maple Street, Sunnyville, AZ', 'David is a passionate environmentalist working to make the world a better place. He is actively involved in conservation efforts and sustainability projects. David enjoys outdoor activities like hiking and camping.', 'sample-8.png');

-- Client 109
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (109, 'sample_sarah', 'sarah0009', 'Sarah', 'Garcia', 'female', '0812345678', 'sample_sarah.pogaty@gmail.com', '456 Birch Street, Lakeside, WA', 'Sarah is a successful entrepreneur with a knack for innovation. She has launched multiple startups and is known for her determination and leadership skills. In her free time, Sarah enjoys mentoring aspiring entrepreneurs and exploring new business opportunities.', 'sample-9.png');

-- Client 110
insert into client (id, username, password, firstname, lastname, gender, phone_number, mail, address, description, file_image) 
values (110, 'sample_michael', 'michael0010', 'Michael', 'Harris', 'male', '0912345678', 'sample_michael.pogaty@gmail.com', '789 Redwood Street, Mountainville, CO', 'Michael is a dedicated firefighter who puts his life on the line to protect the community. His bravery and commitment to public safety are highly respected. In his off-duty hours, Michael enjoys spending time with his family and playing sports.', 'sample-10.png');



-- Problem 101 BY Client 101
insert into problem (id, topic, description, category, idea_visible, client_id, date)
values (101, 'Global Warming', 'Global warming is a long-term, worldwide phenomenon characterized by a gradual increase in the Earth average surface temperature. This rise in temperature is primarily attributed to the enhanced greenhouse effect, which is driven by the accumulation of greenhouse gases in the Earth atmosphere.', 'environment', false, 101, '2023-11-05 16:45:30');

-- Problem 102 BY Client 101
insert into problem (id, topic, description, category, idea_visible, client_id, date)
values (102, 'Renewable Energy', 'Renewable energy sources, such as solar, wind, and hydropower, are crucial in reducing our reliance on fossil fuels and combating climate change. These sources are sustainable and environmentally friendly alternatives.', 'innovation', false, 101, '2023-11-01 16:50:15');

-- Problem 103 BY Client 102
insert into problem (id, topic, description, category, idea_visible, client_id, date)
values (103, 'Plastic Pollution', 'Plastic pollution is a pressing issue as plastic waste accumulates in our oceans and harms marine life. Finding sustainable alternatives to single-use plastics is essential.', 'environment', false, 102, '2023-11-05 16:55:45');

-- Problem 104 BY Client 103
insert into problem (id, topic, description, category, idea_visible, client_id, date)
values (104, 'Educational Equity', 'Achieving educational equity is essential to ensure that all students have access to quality education, regardless of their background. Addressing disparities in resources and opportunities is crucial.', 'global', false, 103, '2023-10-25 17:00:20');

-- Problem 105 BY Client 105
insert into problem (id, topic, description, category, idea_visible, client_id, date)
values (105, 'Cybersecurity Threats', 'With the increasing reliance on digital technologies, the threat of cybersecurity breaches has grown. Protecting sensitive data and infrastructure from cyberattacks is a critical concern.', 'innovation', false, 105, '2023-10-27 17:05:10');



-- Idea 101 ON Problem 101
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (101, 'Develop the carbon detector', 'The concept is to create an innovative device or system capable of accurately detecting and measuring carbon emissions. This carbon detector aims to address environmental concerns and promote sustainability by providing real-time data on carbon levels in various settings, which can be used for monitoring and reducing carbon footprints. The ultimate goal is to contribute to a greener and more eco-conscious future by empowering individuals, businesses, and organizations with the ability to track and mitigate their carbon impact.', 'Introduction - The "Advanced Carbon Detector" is a groundbreaking concept aimed at creating a cutting-edge device for accurate and real-time measurement of carbon emissions. This innovation is driven by the increasing global concern for environmental sustainability and the urgent need to monitor and reduce carbon footprints across various sectors. Environmental Impact - The Advanced Carbon Detector has the potential to make a significant impact on reducing carbon emissions by promoting awareness and accountability. It will empower individuals and organizations to adopt eco-conscious practices and contribute to a greener, more sustainable future.  Future Development - The project will require research and development to ensure the accuracy and reliability of the device. Collaborations with experts in the field, as well as partnerships with environmental organizations, may be sought to enhance its capabilities and reach a wider audience.', true, 101);

-- Participant 101 BY Client 101
insert into participants (id, role, client_id)
values (101, 'creator', 101);

-- Participant 109 BY Client 102
insert into participants (id, role, client_id)
values (109, 'member', 102);

-- Participant 110 BY Client 104
insert into participants (id, role, client_id)
values (110, 'member', 104);

-- Idea 101 BY Participant 101
insert into idea_participants (idea_id, participant_id)
values (101, 101);

-- Idea 101 BY Participant 101
insert into idea_participants (idea_id, participant_id)
values (101, 109);

-- Idea 101 BY Participant 101
insert into idea_participants (idea_id, participant_id)
values (101, 110);



-- Idea 102 ON Problem 101
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (102, 'Smart Recycling Bins', 'The concept is to develop smart recycling bins equipped with sensors and IoT technology to promote recycling and waste management. These bins will be able to notify waste collection services when they are full, optimizing collection routes and reducing environmental impact. This innovative solution aims to make recycling more efficient and environmentally friendly.', 'Introduction - Smart Recycling Bins are a forward-thinking solution to revolutionize waste management. These bins will incorporate sensor technology to detect fill levels, ensuring timely waste collection. Environmental Impact - By optimizing waste collection, these bins will reduce fuel consumption and greenhouse gas emissions, contributing to a cleaner environment. Future Development - Further enhancements may include smart sorting and rewards systems to encourage recycling.', false, 101);

-- Participant 102 BY Client 103
insert into participants (id, role, client_id)
values (102, 'creator', 103);

-- Participant 109 BY Client 102
insert into participants (id, role, client_id)
values (111, 'member', 108);

-- Idea 101 BY Participant 102
insert into idea_participants (idea_id, participant_id)
values (102, 102);

-- Idea 101 BY Participant 102
insert into idea_participants (idea_id, participant_id)
values (102, 111);



-- Idea 103 ON Problem 102
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (103, 'Renewable Energy Microgrids', 'The concept is to create renewable energy microgrids in local communities to provide reliable and sustainable energy sources. These microgrids will harness solar, wind, or other renewable sources to reduce dependence on fossil fuels and promote energy independence. The ultimate goal is to create resilient and eco-friendly energy solutions for communities.', 'Introduction - Renewable Energy Microgrids offer localized, clean, and reliable power sources, reducing greenhouse gas emissions. Environmental Impact - These microgrids can significantly decrease reliance on non-renewable energy sources, making a positive impact on the environment. Future Development - Expanding microgrid infrastructure and integrating energy storage can further enhance their impact.', false, 102);

-- Participant 103 BY Client 102
insert into participants (id, role, client_id)
values (103, 'creator', 102);

-- Idea 103 BY Participant 103
insert into idea_participants (idea_id, participant_id)
values (103, 103);



-- Idea 104 ON Problem 101
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (104, 'Urban Green Spaces Initiative', 'The concept is to launch an Urban Green Spaces Initiative to transform unused urban areas into green, eco-friendly spaces. These initiatives will create parks, gardens, and green infrastructure to improve air quality, provide recreational opportunities, and enhance the overall quality of life in urban environments.', 'Introduction - The Urban Green Spaces Initiative aims to combat urbanization negative effects by promoting green and healthy spaces in cities. Environmental Impact - This initiative will enhance urban biodiversity, reduce heat islands, and improve air quality, positively impacting the environment. Future Development - Expanding green spaces and involving local communities can further drive positive change.', true, 101);

-- Participant 104 BY Client 102
insert into participants (id, role, client_id)
values (104, 'creator', 106);

-- Idea 104 BY Participant 104
insert into idea_participants (idea_id, participant_id)
values (104, 104);



-- Idea 105 ON Problem 105
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (105, 'AI-Powered Cybersecurity Defense', 'The concept is to develop AI-powered cybersecurity defense systems to proactively detect and mitigate cyber threats. These systems will use machine learning algorithms to identify and respond to security breaches in real-time, ensuring the protection of sensitive data and networks.', 'Introduction - AI-Powered Cybersecurity Defense leverages advanced AI technology to defend against evolving cyber threats. Environmental Impact - By safeguarding critical data, this system helps prevent environmental harm caused by potential cyberattacks on industrial systems. Future Development - Continuous training of AI models and integration with various industries can enhance its effectiveness in protecting against cyber threats.', true, 105);

-- Participant 105 BY Client 108
insert into participants (id, role, client_id)
values (105, 'creator', 108);

-- Participant 105 BY Client 108
insert into participants (id, role, client_id)
values (112, 'member', 107);

-- Participant 105 BY Client 108
insert into participants (id, role, client_id)
values (113, 'member', 106);

-- Participant 105 BY Client 108
insert into participants (id, role, client_id)
values (114, 'member', 103);

-- Idea 105 BY Participant 105
insert into idea_participants (idea_id, participant_id)
values (105, 105);

-- Idea 105 BY Participant 105
insert into idea_participants (idea_id, participant_id)
values (105, 112);

-- Idea 105 BY Participant 105
insert into idea_participants (idea_id, participant_id)
values (105, 113);

-- Idea 105 BY Participant 105
insert into idea_participants (idea_id, participant_id)
values (105, 114);


-- Idea 106 ON Problem 104
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (106, 'Digital Inclusion for Educational Equity', 'The concept is to bridge the digital divide and promote educational equity by providing internet access and devices to underserved students. This initiative aims to ensure that all students have equal opportunities to access quality education and resources online.', 'Introduction - Digital Inclusion for Educational Equity focuses on leveling the educational playing field by providing digital resources to underserved students. Environmental Impact - Reducing the digital divide can lead to a decreased reliance on physical educational materials, contributing to environmental sustainability. Future Development - Expanding access and providing digital literacy programs can further promote educational equity.', false, 104);

-- Participant 106 BY Client 101
insert into participants (id, role, client_id)
values (106, 'creator', 101);

-- Idea 106 BY Participant 106
insert into idea_participants (idea_id, participant_id)
values (106, 106);



-- Idea 107 ON Problem 103
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (107, 'Plastic Recycling Innovation', 'The concept is to develop innovative plastic recycling technologies and methods to address plastic pollution. These solutions will focus on efficient plastic recycling and reusing plastic waste to reduce its environmental impact.', 'Introduction - Plastic Recycling Innovation aims to combat plastic pollution by finding new ways to recycle and reuse plastic materials. Environmental Impact - By reducing plastic waste in landfills and oceans, this innovation can significantly contribute to environmental conservation. Future Development - Collaborations with recycling facilities and consumer awareness campaigns can drive the adoption of these innovations.', false, 103);

-- Participant 107 BY Client 104
insert into participants (id, role, client_id)
values (107, 'creator', 104);

-- Idea 107 BY Participant 107
insert into idea_participants (idea_id, participant_id)
values (107, 107);



-- Idea 108 ON Problem 105
insert into idea (id, idea_header, key, board, public_state, problem_id) 
values (108, 'Blockchain-Based Security Solutions', 'The concept is to implement blockchain technology to enhance cybersecurity and protect critical data. Blockchain decentralized and secure nature can be harnessed to prevent data breaches and unauthorized access to sensitive information.', 'Introduction - Blockchain-Based Security Solutions leverage blockchain security features to protect against cybersecurity threats. Environmental Impact - Enhanced data security can prevent environmental damage caused by cyberattacks on critical infrastructure. Future Development - Integrating blockchain into various sectors and constant updates can strengthen data protection.', false, 105);

-- Participant 108 BY Client 104
insert into participants (id, role, client_id)
values (108, 'creator', 104);

-- Idea 108 BY Participant 108
insert into idea_participants (idea_id, participant_id)
values (108, 108);


-- Trend on Problem 101
insert into trend (id, trend, client_id, problem_id) values (101, true, 101, 101);
insert into trend (id, trend, client_id, problem_id) values (102, true, 102, 101);
insert into trend (id, trend, client_id, problem_id) values (103, false, 103, 101);
insert into trend (id, trend, client_id, problem_id) values (104, true, 105, 101);
insert into trend (id, trend, client_id, problem_id) values (105, true, 107, 101);
insert into trend (id, trend, client_id, problem_id) values (106, false, 108, 101);
insert into trend (id, trend, client_id, problem_id) values (107, true, 109, 101);
insert into trend (id, trend, client_id, problem_id) values (108, true, 110, 101);

-- Trend on Problem 102
insert into trend (id, trend, client_id, problem_id) values (109, true, 103, 102);
insert into trend (id, trend, client_id, problem_id) values (110, true, 105, 102);
insert into trend (id, trend, client_id, problem_id) values (111, false, 106, 102);
insert into trend (id, trend, client_id, problem_id) values (112, false, 107, 102);

-- Trend on Problem 103
insert into trend (id, trend, client_id, problem_id) values (113, true, 101, 103);
insert into trend (id, trend, client_id, problem_id) values (114, true, 102, 103);
insert into trend (id, trend, client_id, problem_id) values (115, false, 107, 103);
insert into trend (id, trend, client_id, problem_id) values (116, false, 108, 103);
insert into trend (id, trend, client_id, problem_id) values (117, false, 109, 103);
insert into trend (id, trend, client_id, problem_id) values (118, false, 110, 103);

-- Trend on Problem 104
insert into trend (id, trend, client_id, problem_id) values (119, true, 101, 104);
insert into trend (id, trend, client_id, problem_id) values (120, true, 102, 104);
insert into trend (id, trend, client_id, problem_id) values (121, true, 103, 104);
insert into trend (id, trend, client_id, problem_id) values (122, true, 104, 104);
insert into trend (id, trend, client_id, problem_id) values (123, false, 105, 104);
insert into trend (id, trend, client_id, problem_id) values (124, false, 106, 104);
insert into trend (id, trend, client_id, problem_id) values (125, false, 109, 104);
insert into trend (id, trend, client_id, problem_id) values (126, false, 110, 104);

-- Trend on Problem 105
insert into trend (id, trend, client_id, problem_id) values (127, true, 107, 105);
insert into trend (id, trend, client_id, problem_id) values (128, true, 102, 105);
insert into trend (id, trend, client_id, problem_id) values (129, true, 103, 105);
insert into trend (id, trend, client_id, problem_id) values (130, true, 104, 105);
insert into trend (id, trend, client_id, problem_id) values (131, true, 105, 105);
insert into trend (id, trend, client_id, problem_id) values (132, true, 106, 105);
insert into trend (id, trend, client_id, problem_id) values (133, true, 109, 105);
insert into trend (id, trend, client_id, problem_id) values (134, true, 110, 105);



-- agreement on Idea 101
insert into agreement (id, agreed, client_id, idea_id) values (101, true, 101, 101);
insert into agreement (id, agreed, client_id, idea_id) values (102, true, 102, 101);
insert into agreement (id, agreed, client_id, idea_id) values (103, false, 103, 101);
insert into agreement (id, agreed, client_id, idea_id) values (104, true, 107, 101);
insert into agreement (id, agreed, client_id, idea_id) values (105, true, 108, 101);
insert into agreement (id, agreed, client_id, idea_id) values (106, false, 109, 101);
insert into agreement (id, agreed, client_id, idea_id) values (107, true, 110, 101);

-- Agreement on Idea 102
insert into agreement (id, agreed, client_id, idea_id) values (108, true, 108, 102);
insert into agreement (id, agreed, client_id, idea_id) values (109, true, 109, 102);
insert into agreement (id, agreed, client_id, idea_id) values (110, false, 110, 102);

-- Agreement on Idea 103
insert into agreement (id, agreed, client_id, idea_id) values (111, true, 101, 103);
insert into agreement (id, agreed, client_id, idea_id) values (112, false, 102, 103);
insert into agreement (id, agreed, client_id, idea_id) values (113, true, 103, 103);
insert into agreement (id, agreed, client_id, idea_id) values (114, true, 104, 103);

-- Agreement on Idea 104
insert into agreement (id, agreed, client_id, idea_id) values (115, false, 105, 104);
insert into agreement (id, agreed, client_id, idea_id) values (116, true, 106, 104);
insert into agreement (id, agreed, client_id, idea_id) values (117, true, 107, 104);
insert into agreement (id, agreed, client_id, idea_id) values (118, false, 108, 104);

-- Agreement on Idea 105
insert into agreement (id, agreed, client_id, idea_id) values (119, true, 109, 105);
insert into agreement (id, agreed, client_id, idea_id) values (120, true, 110, 105);
insert into agreement (id, agreed, client_id, idea_id) values (121, true, 101, 105);

-- Agreement on Idea 106
insert into agreement (id, agreed, client_id, idea_id) values (122, true, 102, 106);
insert into agreement (id, agreed, client_id, idea_id) values (123, true, 103, 106);
insert into agreement (id, agreed, client_id, idea_id) values (124, true, 104, 106);

-- Agreement on Idea 107
insert into agreement (id, agreed, client_id, idea_id) values (125, true, 105, 107);
insert into agreement (id, agreed, client_id, idea_id) values (126, true, 106, 107);
insert into agreement (id, agreed, client_id, idea_id) values (127, false, 107, 107);