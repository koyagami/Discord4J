/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package discord4j.core.spec;

import discord4j.common.json.request.GuildEmojiCreateRequest;
import discord4j.core.object.entity.Role;
import discord4j.core.object.util.Snowflake;

import java.util.HashSet;
import java.util.Set;

public class GuildEmojiCreateSpec implements Spec<GuildEmojiCreateRequest> {

    private String name;
    private String image;
    private final Set<Snowflake> roles = new HashSet<>();

    public GuildEmojiCreateSpec setName(String name) {
        this.name = name;
        return this;
    }

    public GuildEmojiCreateSpec setImage(String image) {
        this.image = image;
        return this;
    }

    public GuildEmojiCreateSpec addRole(Snowflake roleId) {
        roles.add(roleId);
        return this;
    }

    public GuildEmojiCreateSpec addRole(Role role) {
        return addRole(role.getId());
    }

    @Override
    public GuildEmojiCreateRequest asRequest() {
        long[] roles = this.roles.stream().mapToLong(Snowflake::asLong).toArray();
        return new GuildEmojiCreateRequest(name, image, roles);
    }
}